package com.tech.TechShopAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Bill_detail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private int id;

    @ManyToOne(targetEntity = Bill.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id",referencedColumnName = "id")
    private Bill bill;

    @ManyToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    private int quantity;
    private int unit_price;
}
