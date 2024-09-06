package com.polymorphisminmodelcreationinjavaspring.entities.order;

import com.polymorphisminmodelcreationinjavaspring.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Audited
public class OrderEntity extends BaseEntity {

    private String orderNumber;
    private Long customerId;
    private Double totalAmount;

}