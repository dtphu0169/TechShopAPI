package com.tech.TechShopAPI.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    private String token;

//    @Enumerated(EnumType.STRING)
//    private AuthenticationProvider authProvider;

//    @OneToMany(targetEntity = Bill.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "accountId",referencedColumnName = "id")
//    private List<Bill> bills;

    @OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId",referencedColumnName = "id")
    List<Address> addressList;
}
