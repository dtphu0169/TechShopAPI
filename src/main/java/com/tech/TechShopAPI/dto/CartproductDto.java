package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Image;
import com.tech.TechShopAPI.model.Product;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@Builder
public class CartproductDto {
    private ProductDto product;
    private int quantity;
}
