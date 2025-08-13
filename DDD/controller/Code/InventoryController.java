package com.example.DDD_test.controller;

import com.example.DDD_test.domain.Inventory;
import com.example.DDD_test.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // 재고 등록 (POST)
    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return service.saveInventory(inventory);
    }

    // 재고 수정 (PUT)
    @PutMapping("/{id}")
    public Inventory updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory inventory) {
        inventory.setId(id);
        return service.saveInventory(inventory);
    }

    // 전체 재고 조회 (GET)
    @GetMapping
    public List<Inventory> getInventoryList() {
        return service.getAllInventories();
    }

    // 특정 재고 조회 (GET)
    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return service.getInventoryById(id);
    }
}
