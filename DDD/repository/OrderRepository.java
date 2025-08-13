package com.example.DDD_test.repository;

import com.example.DDD_test.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}