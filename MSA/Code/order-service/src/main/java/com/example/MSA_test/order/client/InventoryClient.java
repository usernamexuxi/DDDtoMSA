package com.example.MSA_test.order.client;

import com.example.MSA_test.order.dto.ReserveRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "http://inventory-service:8084")
public interface InventoryClient {

    @PutMapping("/inventory/reserve")
    void reserve(@RequestBody ReserveRequest dto);

    @PutMapping("/inventory/rollback")
    void rollback(@RequestBody ReserveRequest dto);
}