package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGen")
    @SequenceGenerator(name = "productIdGen", sequenceName = "productIdGen", allocationSize = 1, initialValue = 6)
    private int productId;
    private String productName;
    private String productDescription;
    private Double price;
    private Double discountedPrice;
    private String appliedDiscount;

}
