package ordermicroservice.order_dao.repository;

import ordermicroservice.order_dao.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    @Query(value = "select * from order_detail where user_id=?1 order by id desc",nativeQuery = true)
    List<OrderDetailEntity> findByIdOrderByIdDesc(Long userId);
}
