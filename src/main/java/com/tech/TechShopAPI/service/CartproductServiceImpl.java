package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CartproductDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.repository.CartproductRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartproductServiceImpl implements CartproductService{
    @Autowired
    CartproductRepository cartproductRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<CartproductDto> getcartproduct(Principal principal) {
        List<CartproductDto> cartproductList = new ArrayList<>();
        for (Cartproduct cartproduct : cartproductRepository.findAllByAccountId(principal.getName())){
            CartproductDto dto = Dtomapper.mapCartproduct(cartproduct);
            cartproductList.add(dto);
        }
        return cartproductList;
    }

    @Override
    public Cartproduct save(Principal principal,CartproductDto cartproductDto) {
        Cartproduct cartproduct = new Cartproduct();
        Product product = productRepository.findById(cartproductDto.getProduct().getId()).get();
        Account account = accountRepository.getbyEmail(principal.getName()).get();

        Optional<Cartproduct> productIncart = cartproductRepository.findInCart(product.getId(),account.getId());
        if (productIncart.isEmpty()){
            cartproduct.setAccount(account);
            cartproduct.setProduct(product);
            cartproduct.setQuantity(cartproductDto.getQuantity());
        } else {
            cartproduct = productIncart.get();
            cartproduct.setQuantity(cartproduct.getQuantity() + cartproductDto.getQuantity());
        }

        cartproductRepository.save(cartproduct);
        return cartproduct;
    }

    @Override
    public boolean delete(Principal principal, CartproductDto cartproductDto) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Cartproduct productIncart = cartproductRepository.findInCart(cartproductDto.getProduct().getId(), account.getId()).get();
        int quantity = (productIncart.getQuantity() - cartproductDto.getQuantity()) > 0 ?
                (productIncart.getQuantity() - cartproductDto.getQuantity()) : 0;
        productIncart.setQuantity(quantity);
        cartproductRepository.save(productIncart);
        return true;
    }
}
