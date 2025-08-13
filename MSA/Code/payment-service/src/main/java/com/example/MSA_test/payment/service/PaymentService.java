package com.example.MSA_test.payment.service;

import com.example.MSA_test.payment.entity.PaymentEntity;
import com.example.MSA_test.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service @RequiredArgsConstructor @Transactional
public class PaymentService {

    private final PaymentRepository repo;

    public void pay(Long orderId, Double amount){
        PaymentEntity save = PaymentEntity.builder()
                .orderId(orderId)
                .paymentMethod("CARD")
                .paymentStatus("COMPLETED")
                .paidAmount(BigDecimal.valueOf(amount))
                .paidAt(LocalDateTime.now())
                .build();
        repo.save(save);
    }
}