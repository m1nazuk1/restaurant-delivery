package com.restaurant.controller;

import com.restaurant.dto.OrderItemDTO.OrderItemRequestDTO;
import com.restaurant.dto.OrderItemDTO.OrderItemResponseDTO;
import com.restaurant.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public OrderItemResponseDTO addOrderItem(@RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        return orderItemService.addOrderItem(orderItemRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderItemResponseDTO updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        return orderItemService.updateOrderItem(id, orderItemRequestDTO);
    }

    @GetMapping("/{id}")
    public OrderItemResponseDTO getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping
    public List<OrderItemResponseDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }
}