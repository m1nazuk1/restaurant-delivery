package com.restaurant.service;

import com.restaurant.dto.OrderItemDTO.OrderItemRequestDTO;
import com.restaurant.dto.OrderItemDTO.OrderItemResponseDTO;
import com.restaurant.entity.Table;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.entity.Delivery;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.OrderItem;
import com.restaurant.repository.DeliveryRepository;
import com.restaurant.repository.MenuItemRepository;
import com.restaurant.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Transactional
    public OrderItemResponseDTO addOrderItem(OrderItemRequestDTO dto) {
        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));

        Optional<OrderItem> existingOrderItem = orderItemRepository.findByMenuItemAndDelivery(menuItem, delivery);

        OrderItem orderItem;
        if (existingOrderItem.isPresent()) {
            orderItem = existingOrderItem.get();
            orderItem.setQuantity(orderItem.getQuantity() + dto.getQuantity());
            orderItem.setAmount(menuItem.getPrice() * orderItem.getQuantity());
        } else {
            orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setDelivery(delivery);
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setAmount(menuItem.getPrice() * dto.getQuantity());
            orderItemRepository.save(orderItem);
        }

        delivery.setAmount(delivery.getAmount() + orderItem.getAmount());
        deliveryRepository.save(delivery);

        return toResponseDTO(orderItem);
    }

    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found"));

        orderItemRepository.delete(orderItem);
    }

    @Transactional
    public OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO dto) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found"));

        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        if (orderItem.getMenuItem().equals(menuItem)) {
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setAmount(menuItem.getPrice() * dto.getQuantity());

            orderItemRepository.save(orderItem);

            Delivery delivery = orderItem.getDelivery();
            delivery.setAmount(delivery.getAmount() + orderItem.getAmount());
            deliveryRepository.save(delivery);

            return toResponseDTO(orderItem);
        } else {
            throw new EntityNotFoundException("Menu item mismatch");
        }
    }

    public List<OrderItemResponseDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public OrderItemResponseDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found"));
        return toResponseDTO(orderItem);
    }

    private OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(orderItem.getId());
        dto.setMenuItemName(orderItem.getMenuItem().getName());
        dto.setQuantity(orderItem.getQuantity());
        dto.setAmount(orderItem.getAmount());
        dto.setDeliveryId(orderItem.getDelivery().getId());
        return dto;
    }
}