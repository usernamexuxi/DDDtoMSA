package com.example.MSA_test.order.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayRequest {
    private Long orderId;
    private BigDecimal amount;
}