package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CartproductDto;
import com.tech.TechShopAPI.model.Cartproduct;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface CartproductService {
    List<CartproductDto> getcartproduct(Principal principal);

    Cartproduct save(Principal principal,CartproductDto cartproductDto);

    boolean delete(Principal principal, CartproductDto cartproductDto);
}
