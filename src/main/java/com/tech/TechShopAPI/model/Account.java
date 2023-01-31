package com.tech.TechShopAPI.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {


    @GeneratedValue(strategy = GenerationType.AUTO)
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


}
