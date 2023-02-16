package com.tech.TechShopAPI.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class AddressDto {
    private int id;
    private String provine_city;
    private String dictrict;
    private String ward;
    private String detail;
}
