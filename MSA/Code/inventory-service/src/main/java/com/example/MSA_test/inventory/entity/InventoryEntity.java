package com.example.MSA_test.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InventoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Column(nullable = false)
    private Integer stock;
}