package com.tech.TechShopAPI.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private int id;
    private String userName;
    private String phone;
    private String email;
    private String role;
    private boolean active;
    private String password;
    private Date registerDate;
    private String token;

//    @OneToMany(targetEntity = Bill.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "accountId",referencedColumnName = "id")
//    private List<Bill> bills;
}
