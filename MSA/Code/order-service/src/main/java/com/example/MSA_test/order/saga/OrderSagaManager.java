package com.example.MSA_test.order.saga;

import com.example.MSA_test.common.enums.OrderStatus;
import com.example.MSA_test.common.exception.BusinessException;
import com.example.MSA_test.common.exception.NotFoundException;
import com.example.MSA_test.order.client.InventoryClient;
import com.example.MSA_test.order.client.PaymentClient;
import com.example.MSA_test.order.dto.PayRequest;
import com.example.MSA_test.order.dto.ReserveRequest;
import com.example.MSA_test.order.entity.OrderEntity;
import com.example.MSA_test.order.repository.OrderItemRepository;
import com.example.MSA_test.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderSagaManager {

    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderEntity completeOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("order " + orderId));

        // 각 주문 항목 재고 예약
        orderItemRepository.findByOrderId(orderId).forEach(item ->
                inventoryClient.reserve(ReserveRequest.builder()
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .build())
        );

        // 주문 총액 계산 (order_items 합산)
        BigDecimal totalAmount = orderItemRepository.findTotalAmountByOrderId(orderId);

        // 결제 처리
        try {
            paymentClient.pay(PayRequest.builder()
                    .orderId(order.getId())
                    .amount(totalAmount) // BigDecimal 그대로 전달
                    .build());
        } catch (Exception e) {
            // 결제 실패 시 재고 롤백
            orderItemRepository.findByOrderId(orderId).forEach(item ->
                    inventoryClient.rollback(ReserveRequest.builder()
                            .productName(item.getProductName())
                            .quantity(item.getQuantity())
                            .build())
            );
            throw new BusinessException("payment failed: " + e.getMessage());
        }

        // 주문 상태 완료로 변경
        order.setStatus(OrderStatus.COMPLETED);
        return orderRepository.save(order);
    }
}
