package com.tech.TechShopAPI.dto;

import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.model.Category;

public class Dtomapper {
    public static CategoryDto mapCategory(Category category){
        CategoryDto result = new CategoryDto();
        result.setId(category.getId());
        result.setName(category.getName());
        result.setImage_path(category.getImage_path());
        return result;
    }

    public static CartproductDto mapCartproduct(Cartproduct cartproduct){
        CartproductDto result = new CartproductDto();
        result.setProductId(cartproduct.getProduct().getId());
        result.setQuantity(cartproduct.getQuantity());
        return result;
    }
}
