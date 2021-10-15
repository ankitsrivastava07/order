package ordermicroservice.payment_dao.repository;

import ordermicroservice.payment_dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}