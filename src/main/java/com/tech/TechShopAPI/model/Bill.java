package com.tech.TechShopAPI.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@ToString
public class Bill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private long id;

    @ManyToOne(targetEntity = Account.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId",referencedColumnName = "id")
    private Account account;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreate;
    private int price;
    private int shipprice;
    private String address;
    private String phone;
    private boolean paid;
    private String status;
    private String transactionNo;
    private String note;

    @OneToMany(targetEntity = Bill_detail.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id",referencedColumnName = "id")
    private List<Bill_detail> billDetails;
}