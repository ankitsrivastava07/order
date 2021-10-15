package ordermicroservice.dao.base_entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@SuppressWarnings("rawtypes")
public class BaseEntity implements Serializable, Persistable {

    private static final long serialVersionUID = 3461536621459922228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;

    @CreatedDate
    @Basic
    @Column(name = "createdDate", nullable = false, insertable = true, updatable = true)
    private Date createdDate;

    @LastModifiedDate
    @Basic
    @Column(name = "updatedDate", nullable = false, insertable = true, updatable = true)
    private Date updatedDate;

    @Override
    public Object getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return this.id==null;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}