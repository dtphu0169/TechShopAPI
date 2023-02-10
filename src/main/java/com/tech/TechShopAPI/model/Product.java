package com.tech.TechShopAPI.model;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private int id;
    private String name;
    private boolean active;
    private int category_id;
    private int price;
    @Column(nullable = true)
    private Integer label_id;
    private int quantity;
    private String decription;
}
