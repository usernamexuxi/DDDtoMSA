package com.example.DDD_test.service;

import com.example.DDD_test.domain.Inventory;
import com.example.DDD_test.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class InventoryService {
    private final InventoryRepository repo;
    public InventoryService(InventoryRepository repo) { this.repo = repo; }

    public Inventory saveInventory(Inventory inventory) {
        return repo.save(inventory);
    }

    public Inventory getInventoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("재고 없음: " + id));
    }

    public List<Inventory> getAllInventories() {
        return repo.findAll();
    }

    public void decreaseStock(String productName, int qty) {
        Inventory inv = repo.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("재고 없음: " + productName));
        if (inv.getStock() < qty) {
            throw new RuntimeException("재고 부족: " + productName);
        }
        inv.setStock(inv.getStock() - qty);
        repo.save(inv);
    }
}
