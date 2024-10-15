package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements ICustomerOrderService {

    private final CustomerOrderRepository repository;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerOrder createOrder(String email, String address, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(null, email, address, new Date(), items);
        return repository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOpt = repository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.addOrderItem(item);
            repository.save(order);
        });
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOpt = repository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.removeOrderItem(item);
            repository.save(order);
        });
    }

    @Override
    public void applyDiscountToOrder(Long orderId, Discount discount) {
        Optional<CustomerOrder> orderOpt = repository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.applyDiscount(discount);
            repository.save(order);
        });
    }

    @Override
    public void sendOrderForDelivery(Long orderId) {
        Optional<CustomerOrder> orderOpt = repository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.sendForDelivery();
            repository.save(order);
        });
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        Optional<CustomerOrder> orderOpt = repository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.updateDeliveryStatus(status);
            repository.save(order);
        });
    }

    @Override
    public CustomerOrder getOrderById(Long orderId) {
        return repository.findById(orderId).orElse(null);
    }

    @Override
    public List<CustomerOrder> getOrdersByCustomerEmail(String email) {
        return repository.findByCustomerEmail(email);
    }
}
