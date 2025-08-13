package com.example.MSA_test.inventory.repository;

import com.example.MSA_test.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    Optional<InventoryEntity> findByProductName(String productName);
}