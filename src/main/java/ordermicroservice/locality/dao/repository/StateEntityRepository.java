package ordermicroservice.locality.dao.repository;

import ordermicroservice.locality.dao.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateEntityRepository extends JpaRepository<StateEntity, Long> {
}