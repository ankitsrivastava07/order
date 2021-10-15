package ordermicroservice.locality.dao.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="cities")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="pin_code",nullable = false)
    private String pinCode;
    @ManyToOne(targetEntity = StateEntity.class)
    @JoinColumn(name="state_id",referencedColumnName = "id",nullable = false)
    private StateEntity state;
}
