package com.tech.TechShopAPI.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Builder
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private int id;
    @ManyToOne(targetEntity = Account.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
    @ManyToOne(targetEntity = Product.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;
    private String comment;
    private int star;

}