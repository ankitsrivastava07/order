package ordermicroservice.service;

import ordermicroservice.dto.OrderRequest;
import ordermicroservice.dto.OrderResponseDto;
import ordermicroservice.order_dao.entity.OrderDetailEntity;

import java.util.List;

public interface OrderService {
    OrderResponseDto saveOrder(String authencation,OrderRequest orderRequest);

    List<OrderDetailEntity> getAllOrders(Long userId);
}
