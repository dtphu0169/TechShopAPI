package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Cartproduct;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface CartproductService {
    List<Cartproduct> getcartproduct(Principal principal);
}
