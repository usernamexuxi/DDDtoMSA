package com.example.MSA_test.common.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private Double paidAmount;
    private String paidAt;
}