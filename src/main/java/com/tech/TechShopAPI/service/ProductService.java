package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.response.PaginationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void saveProduct(Product product);

    void editProduct(Product product);

    List<Product> findAllAvailable();

    PaginationResponse<Product> findAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
}
