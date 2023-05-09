package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Product;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class LabelDto {
    private int id;
    private String name;
    private double price_rate;
}
