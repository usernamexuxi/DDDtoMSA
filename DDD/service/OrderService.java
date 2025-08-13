package com.example.DDD_test.service;

import com.example.DDD_test.domain.Order;
import com.example.DDD_test.domain.OrderItem;
import com.example.DDD_test.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service @Transactional
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemService itemService;
    private final InventoryService invService;

    public OrderService(OrderRepository orderRepo,
                        OrderItemService itemService,
                        InventoryService invService) {
        this.orderRepo = orderRepo;
        this.itemService = itemService;
        this.invService = invService;
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order completeOrder(Long orderId) {
        // 1) 주문 조회
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음: " + orderId));

        // 2) 주문 상태를 COMPLETED로 변경
        order.setStatus("COMPLETED");

        // 3) 주문항목(order_items) 조회 후 재고 차감
        List<OrderItem> items = itemService.getByOrderId(orderId);
        for (OrderItem oi : items) {
            invService.decreaseStock(oi.getProductName(), oi.getQuantity());
        }

        // 4) 변경된 주문 저장 (UPDATE)
        return orderRepo.save(order);
    }

    // 상태 업데이트용
    public Order updateStatus(Long orderId, String newStatus) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음: " + orderId));
        order.setStatus(newStatus);
        return orderRepo.save(order);
    }
}