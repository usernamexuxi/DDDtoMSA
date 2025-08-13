package com.example.MSA_test.payment.controller;

import com.example.MSA_test.payment.service.PaymentService;
import lombok.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService svc;

    @PostMapping
    public void pay(@RequestBody PayRequest req){
        svc.pay(req.getOrderId(), req.getPaidAmount());
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class PayRequest {
        private Long orderId;
        private Double paidAmount;
    }
}