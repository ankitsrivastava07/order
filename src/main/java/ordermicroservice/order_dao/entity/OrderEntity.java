package ordermicroservice.order_dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="product_id",nullable = false)
    private Long productId;
    @Column(name="price",nullable = false)
    private Double price;
    @Column(name="subcategory_id",nullable = false)
    private Long subCategoryId;
    @ManyToOne
    @JoinColumn(name="order_detail_id",referencedColumnName = "id")
    private OrderDetailEntity orders;
}
