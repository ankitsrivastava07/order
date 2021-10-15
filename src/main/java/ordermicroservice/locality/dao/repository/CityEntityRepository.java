package ordermicroservice.locality.dao.repository;

import ordermicroservice.locality.dao.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityEntityRepository extends JpaRepository<CityEntity, Long> {
}