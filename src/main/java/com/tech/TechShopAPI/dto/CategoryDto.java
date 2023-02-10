package com.tech.TechShopAPI.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class CategoryDto {
    int id;
    private String name;
    private String image_path;
}
