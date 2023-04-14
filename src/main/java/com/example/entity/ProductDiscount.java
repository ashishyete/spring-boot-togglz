package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productDiscountIdGen")
    @SequenceGenerator(name = "productDiscountIdGen", sequenceName = "productDiscountIdGen", allocationSize = 1, initialValue = 1)
    private int id;
    private String productName;
    private int discountVal;

}
