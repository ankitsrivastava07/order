package ordermicroservice.order_dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ordermicroservice.address_dao.entity.AddressEntity;
import ordermicroservice.payment_dao.entity.PaymentEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name="order_detail")
public class  OrderDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="grand_total",nullable = false)
    private Double grandTotal;
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "orders",targetEntity = OrderEntity.class)
    private List<OrderEntity> orderProducts= new ArrayList<>();
    @Column(name="created_at",nullable = false)
    private Date createdAt;
    @Column(name="updated_at",nullable = false)
    private Date updatedAt;
    @Column(name="orderType",nullable = false)
    private String orderType;
    @Column(name="order_status",nullable = false)
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "payment_id",referencedColumnName = "id")
    private PaymentEntity payment;
    @Column(name="user_id",nullable = false)
    private Long userId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private AddressEntity address;
    @Column(name="random_id",nullable = false)
    private String randomId;

    @PrePersist
    public void prePersist(){
        this.createdAt= new Date();
        this.updatedAt=new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.createdAt= new Date();
        this.updatedAt=new Date();
    }

}
