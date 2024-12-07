package com.restaurant.service;

import com.restaurant.dto.DeliveryDTO.DeliveryRequestDTO;
import com.restaurant.dto.DeliveryDTO.DeliveryResponseDTO;
import com.restaurant.entity.Delivery;
import com.restaurant.entity.Visitor;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.repository.DeliveryRepository;
import com.restaurant.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    public List<DeliveryResponseDTO> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public DeliveryResponseDTO getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
        return toResponseDTO(delivery);
    }

    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO dto) {
        Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found"));
        Delivery delivery = new Delivery();
        delivery.setDateTime(LocalDateTime.now());
        delivery.setAmount(0.0);
        delivery.setPaymentMethod(dto.getPaymentMethod());
        delivery.setAddress(dto.getAddress());
        delivery.setStatus("Pending");
        delivery.setComment(dto.getComment());
        delivery.setType(dto.getType());
        delivery.setVisitor(visitor);
        deliveryRepository.save(delivery);
        return toResponseDTO(delivery);
    }

    private DeliveryResponseDTO toResponseDTO(Delivery delivery) {
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setId(delivery.getId());
        dto.setDateTime(delivery.getDateTime());
        dto.setAmount(delivery.getAmount());
        dto.setPaymentMethod(delivery.getPaymentMethod());
        dto.setAddress(delivery.getAddress());
        dto.setStatus(delivery.getStatus());
        dto.setComment(delivery.getComment());
        dto.setType(delivery.getType());
        return dto;
    }
}