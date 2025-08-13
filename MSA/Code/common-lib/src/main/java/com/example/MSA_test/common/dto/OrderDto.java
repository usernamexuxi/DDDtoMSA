package com.example.MSA_test.common.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private String status;
    private String orderDate;
    private Double totalAmount;
}