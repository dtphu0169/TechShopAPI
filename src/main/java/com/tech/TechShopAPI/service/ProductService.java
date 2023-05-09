package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.ProductDto;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.response.PaginationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void saveProduct(ProductDto product);

    void editProduct(ProductDto product);

    List<ProductDto> findAllAvailable();

    PaginationResponse<ProductDto> findAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);

    List<ProductDto> findAll();

    ProductDto findById(int id);

    List<ProductDto> searchByName(String name);
}
