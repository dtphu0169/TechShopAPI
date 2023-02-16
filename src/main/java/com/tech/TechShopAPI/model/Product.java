package com.tech.TechShopAPI.model;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(targetEntity = Label.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "label_id",referencedColumnName = "id")
//    @Column(nullable = true)
    private Label label;

    private int quantity;
    private String decription;

    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId",referencedColumnName = "id")
    List<Image> images ;

    @OneToMany(targetEntity = Feedback.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId",referencedColumnName = "id")
    List<Feedback> feedbacks ;
}
