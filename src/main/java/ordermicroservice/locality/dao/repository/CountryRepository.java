package ordermicroservice.locality.dao.repository;

import ordermicroservice.locality.dao.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
}
