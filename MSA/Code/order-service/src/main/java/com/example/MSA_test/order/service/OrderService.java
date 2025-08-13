package com.example.MSA_test.order.service;

import com.example.MSA_test.common.enums.OrderStatus;
import com.example.MSA_test.order.entity.OrderEntity;
import com.example.MSA_test.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;

    public OrderEntity create(OrderEntity entity) {
        entity.setStatus(OrderStatus.PENDING);
        entity.setOrderDate(LocalDateTime.now());
        return repository.save(entity);
    }

    public List<OrderEntity> list() {
        return repository.findAll();
    }
}