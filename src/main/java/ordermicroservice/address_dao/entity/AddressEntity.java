package ordermicroservice.address_dao.entity;
import lombok.Data;
import ordermicroservice.locality.dao.entity.CityEntity;
import ordermicroservice.locality.dao.entity.CountryEntity;
import ordermicroservice.locality.dao.entity.StateEntity;
import ordermicroservice.order_dao.entity.OrderDetailEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name="address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name="street1" ,nullable = false)
    private String street1;
    @Column(name="land_mark",nullable = false)
    private String landMark;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="city_id",nullable = true)
    private CityEntity cityEntity;
}
