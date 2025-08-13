package com.example.MSA_test.order.repository;

import com.example.MSA_test.order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrderId(Long orderId);

    @Query("SELECT COALESCE(SUM(oi.totalPrice), 0) " +
            "FROM OrderItemEntity oi " +
            "WHERE oi.orderId = :orderId")
    BigDecimal findTotalAmountByOrderId(Long orderId);
}