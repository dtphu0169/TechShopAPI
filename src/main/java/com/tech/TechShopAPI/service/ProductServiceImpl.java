package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void editProduct(Product product) {
        Product productData = productRepository.findById(product.getId()).get();
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllAvailable() {
        return productRepository.findAllAvailable();
    }
}
