package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Address;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@Builder
public class AccountDto {
    private int id;
    private String userName;
    private String phone;
    private String email;
    private String role;
    private boolean active;
    private Date registerDate;
    private List<AddressDto> addressList;
}
