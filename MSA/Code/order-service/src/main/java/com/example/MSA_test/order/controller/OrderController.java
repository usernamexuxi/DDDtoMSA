package com.example.MSA_test.order.controller;

import com.example.MSA_test.order.entity.OrderEntity;
import com.example.MSA_test.order.saga.OrderSagaManager;
import com.example.MSA_test.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderSagaManager sagaManager;

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.create(order));
    }

    @GetMapping
    public List<OrderEntity> list() {
        return orderService.list();
    }

    // 주문 완료 Saga 트랜잭션 실행 API
    @PutMapping("/{id}/complete")
    public ResponseEntity<OrderEntity> complete(@PathVariable Long id) {
        return ResponseEntity.ok(sagaManager.completeOrder(id));
    }
}