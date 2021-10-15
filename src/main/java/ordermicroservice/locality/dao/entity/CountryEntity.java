package ordermicroservice.locality.dao.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="countries")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="short_name",nullable = false)
    private String shortName;
    @Column(name="phone_code",nullable = false)
    private String phoneCode;

}
