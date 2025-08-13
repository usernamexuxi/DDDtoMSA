package com.example.MSA_test.inventory.controller;

import com.example.MSA_test.inventory.entity.InventoryEntity;
import com.example.MSA_test.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/inventory") @RequiredArgsConstructor
public class InventoryController {

    private final InventoryService svc;

    @PostMapping
    public ResponseEntity<InventoryEntity> create(@RequestBody InventoryEntity dto){
        return ResponseEntity.ok(svc.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryEntity> update(
            @PathVariable Long id, @RequestBody InventoryEntity dto){
        dto.setId(id);
        return ResponseEntity.ok(svc.save(dto));
    }

    @GetMapping
    public List<InventoryEntity> list(){ return svc.list(); }

    // 재고 차감(Feign용)
    @PutMapping("/reserve")
    public ResponseEntity<Void> reserve(@RequestBody InventoryEntity dto){
        svc.reserve(dto.getProductName(), dto.getStock());
        return ResponseEntity.ok().build();
    }

    // 재고 롤백(Feign용)
    @PutMapping("/rollback")
    public ResponseEntity<Void> rollback(@RequestBody InventoryEntity dto){
        svc.rollback(dto.getProductName(), dto.getStock());
        return ResponseEntity.ok().build();
    }
}