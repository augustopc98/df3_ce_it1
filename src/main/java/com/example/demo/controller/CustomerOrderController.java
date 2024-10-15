package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;
import com.example.demo.service.ICustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    private final ICustomerOrderService service;

    @Autowired
    public CustomerOrderController(ICustomerOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        CustomerOrder createdOrder = service.createOrder(order.getCustomerEmail(), order.getCustomerAddress(), order.getItems());
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable Long id) {
        CustomerOrder order = service.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{orderId}/add-item")
    public ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        service.addOrderItem(orderId, item);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/apply-discount")
    public ResponseEntity<String> applyDiscount(
            @PathVariable Long orderId, @RequestBody Discount discount) {
        service.applyDiscountToOrder(orderId, discount);
        return ResponseEntity.ok("Discount applied successfully.");
    }
}
