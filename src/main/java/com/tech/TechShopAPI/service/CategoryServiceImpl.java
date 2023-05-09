package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CategoryDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.dto.ProductDto;
import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.CategorySpecifications;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getProductByCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        Specification<Category> specification = CategorySpecifications.hasCategoryNameLike(categoryName);
        Optional<Category> categorydata = categoryRepository.findbyName(categoryName);
        if (!categorydata.isEmpty()){
            Category category = categorydata.get();
            products = category.getProducts();
        }
        return Dtomapper.mapProductList(products);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> categoriList = new ArrayList<CategoryDto>();
        for (Category c : categoryRepository.findAll()){
            categoriList.add(Dtomapper.mapCategory(c));
        }
        return categoriList;
    }

}
