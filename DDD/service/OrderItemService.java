package com.example.DDD_test.service;

import com.example.DDD_test.domain.OrderItem;
import com.example.DDD_test.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class OrderItemService {
    private final OrderItemRepository repo;
    public OrderItemService(OrderItemRepository repo) { this.repo = repo; }

    public OrderItem addOrderItem(OrderItem orderItem) {
        orderItem.setTotalPrice(orderItem.getQuantity() * orderItem.getUnitPrice());
        return repo.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return repo.findAll();
    }

    public List<OrderItem> getByOrderId(Long orderId) {
        return repo.findByOrderId(orderId);
    }
}