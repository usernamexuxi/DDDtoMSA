package com.example.MSA_test.order.client;

import com.example.MSA_test.order.dto.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://payment-service:8083")
public interface PaymentClient {
    @PostMapping("/payments/pay")
    void pay(@RequestBody PayRequest dto);
}