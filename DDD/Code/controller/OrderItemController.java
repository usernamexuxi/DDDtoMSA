package com.example.DDD_test.controller;

import com.example.DDD_test.domain.OrderItem;
import com.example.DDD_test.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService service;
    public OrderItemController(OrderItemService service) { this.service = service; }

    @PostMapping
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        return service.addOrderItem(orderItem);
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return service.getAllOrderItems();
    }
}
