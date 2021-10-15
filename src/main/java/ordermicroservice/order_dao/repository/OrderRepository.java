package ordermicroservice.order_dao.repository;

import ordermicroservice.order_dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
