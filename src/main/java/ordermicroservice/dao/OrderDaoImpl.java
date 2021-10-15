package ordermicroservice.dao;

import ordermicroservice.order_dao.entity.OrderDetailEntity;
import ordermicroservice.order_dao.entity.OrderEntity;
import ordermicroservice.order_dao.repository.OrderDetailRepository;
import ordermicroservice.order_dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private OrderRepository orderRepository;
    @Override
    @Transactional
    public OrderDetailEntity saveOrder(OrderDetailEntity orderDetailEntity) {
        OrderDetailEntity detail=orderDetailRepository.save(orderDetailEntity);
        List<OrderEntity>list=detail.getOrderProducts();
        list.stream().forEach(order->order.setOrders(detail));
        orderRepository.saveAll(list);
        return detail;
    }
    public List<OrderDetailEntity> getAllOrders(Long userId){
        List<OrderDetailEntity> list= orderDetailRepository.findByIdOrderByIdDesc(userId);
        return list;
    }
}

