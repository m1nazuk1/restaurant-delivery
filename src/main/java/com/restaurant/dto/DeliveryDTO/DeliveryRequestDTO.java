package com.restaurant.dto.DeliveryDTO;

import com.restaurant.dto.OrderItemDTO.OrderItemRequestDTO;
import lombok.Data;

import java.util.List;

public class DeliveryRequestDTO {
    private String paymentMethod;
    private String address;
    private String comment;
    private String type; // тип доставки (напр., курьером, самовывоз)
    private List<OrderItemRequestDTO> orderItems; // список товаров в заказе
    private Long visitorId;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemRequestDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }
}