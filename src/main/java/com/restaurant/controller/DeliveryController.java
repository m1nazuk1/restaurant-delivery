package com.restaurant.controller;

import com.restaurant.dto.DeliveryDTO.DeliveryRequestDTO;
import com.restaurant.dto.DeliveryDTO.DeliveryResponseDTO;
import com.restaurant.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryResponseDTO> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryResponseDTO getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping
    public DeliveryResponseDTO createDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        return deliveryService.createDelivery(deliveryRequestDTO);
    }

    @PutMapping("/{id}")
    public DeliveryResponseDTO updateDelivery(@PathVariable Long id, @RequestBody DeliveryRequestDTO dto) {
        return deliveryService.updateDelivery(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {

    }
}