package ordermicroservice.payment_dao.entity;

import lombok.Data;
import ordermicroservice.order_dao.entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name="payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="transaction_id")
    private String transactionId;
    @Column(name="payment_status")
    private String paymentStatus;
    @Column(name="amount")
    private Double amount;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @Column(name="payment_mode")
    private String paymentMode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private OrderDetailEntity orderdetailEntity;
}
