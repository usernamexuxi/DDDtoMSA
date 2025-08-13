package com.example.MSA_test.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveRequest {
    private String productName;
    private Integer quantity;
}
