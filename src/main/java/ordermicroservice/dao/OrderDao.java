package ordermicroservice.dao;

import ordermicroservice.dto.OrderResponseDto;
import ordermicroservice.order_dao.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDao {
    OrderDetailEntity saveOrder(OrderDetailEntity orderDetailEntity);

    List<OrderDetailEntity> getAllOrders(Long userId);
}
