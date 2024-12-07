package com.restaurant.repository;

import com.restaurant.entity.Delivery;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByDeliveryId(Long deliveryId);

    Optional<OrderItem> findByMenuItemAndDelivery(MenuItem menuItem, Delivery delivery);
}