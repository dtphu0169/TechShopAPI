package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CartproductDto;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.repository.CartproductRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
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
    public List<Cartproduct> getcartproduct(Principal principal) {
        List<Cartproduct> cartproductList = cartproductRepository.findAllByAccountId(principal.getName());
        return cartproductList;
    }

    @Override
    public Cartproduct save(Principal principal,CartproductDto cartproductDto) {
        Cartproduct cartproduct = new Cartproduct();
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Product product = productRepository.findById(cartproductDto.getProductId()).get();

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
        Cartproduct productIncart = cartproductRepository.findInCart(cartproductDto.getProductId(), account.getId()).get();
        int quantity = (productIncart.getQuantity() - cartproductDto.getQuantity()) > 0 ?
                (productIncart.getQuantity() - cartproductDto.getQuantity()) : 0;
        productIncart.setQuantity(quantity);
        cartproductRepository.save(productIncart);
        return true;
    }
}
