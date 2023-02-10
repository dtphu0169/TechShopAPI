package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CategoryDto;
import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Product> getProductByCategory(String categoryName);

    List<CategoryDto> getAllCategory();
}
