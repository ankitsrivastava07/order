package ordermicroservice.locality.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="states")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="short_name",nullable = false)
    private String shortName;
    @Column(name="phone_code",nullable = false)
    private String phoneCode;
    @ManyToOne(targetEntity = CountryEntity.class,cascade = CascadeType.MERGE)
    @JoinColumn(name="country_id",referencedColumnName = "id",nullable = false)
    private CountryEntity countryEntity;
}
