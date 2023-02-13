package com.tech.TechShopAPI.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class AccountDto {
    private int id;
    private String userName;
    private String phone;
    private String email;
    private String role;
    private boolean active;
    private Date registerDate;
}
