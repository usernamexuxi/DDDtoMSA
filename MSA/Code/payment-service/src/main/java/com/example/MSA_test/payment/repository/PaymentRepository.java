package com.example.MSA_test.payment.repository;
import com.example.MSA_test.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> { }