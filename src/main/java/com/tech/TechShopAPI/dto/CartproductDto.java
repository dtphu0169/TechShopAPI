package com.tech.TechShopAPI.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class CartproductDto {
    private int productId;
    private int quantity;
}
