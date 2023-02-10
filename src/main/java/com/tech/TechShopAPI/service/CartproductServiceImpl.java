package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.repository.CartproductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class CartproductServiceImpl implements CartproductService{
    @Autowired
    CartproductRepository cartproductRepository;

    @Override
    public List<Cartproduct> getcartproduct(Principal principal) {
        List<Cartproduct> cartproductList = cartproductRepository.findAllByAccountId(principal.getName());
        return cartproductList;
    }
}
