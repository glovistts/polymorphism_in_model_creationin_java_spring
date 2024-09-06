package com.polymorphisminmodelcreationinjavaspring.entities.product;

import com.polymorphisminmodelcreationinjavaspring.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "products")
@Getter
@Setter
@Audited
public class ProductEntity extends BaseEntity {

    private String name;
    private String description;
    private Double price;

}
