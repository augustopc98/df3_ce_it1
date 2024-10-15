package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;

import java.util.List;

public interface ICustomerOrderService {
    CustomerOrder createOrder(String email, String address, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    void applyDiscountToOrder(Long orderId, Discount discount);
    void sendOrderForDelivery(Long orderId);
    void updateDeliveryStatus(Long orderId, String status);
    CustomerOrder getOrderById(Long orderId);
    List<CustomerOrder> getOrdersByCustomerEmail(String email);
}
