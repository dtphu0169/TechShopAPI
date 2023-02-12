package com.tech.TechShopAPI.payload.request;

import com.tech.TechShopAPI.dto.CartproductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private int price;
    private int shipprice;
    private String address;
    private String phone;
    private boolean paid;
    private String note;
    private List<CartproductDto> details = new ArrayList<CartproductDto>();
}
