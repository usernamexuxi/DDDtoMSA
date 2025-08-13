package com.example.MSA_test.inventory.service;

import com.example.MSA_test.inventory.entity.InventoryEntity;
import com.example.MSA_test.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryEntity save(InventoryEntity e){ return repo.save(e); }

    public List<InventoryEntity> list(){ return repo.findAll(); }

    public InventoryEntity find(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("inventory " + id));
    }

    public void reserve(String productName, int qty){
        InventoryEntity inv = repo.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("inventory " + productName));
        if(inv.getStock() < qty) throw new RuntimeException("stock shortage");
        inv.setStock(inv.getStock() - qty);
        repo.save(inv);
    }

    public void rollback(String productName, int qty){
        InventoryEntity inv = repo.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("inventory " + productName));
        inv.setStock(inv.getStock() + qty);
        repo.save(inv);
    }
}