package com.tech.TechShopAPI.model;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {
    public static Specification<Category> hasCategoryNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("name"), "%" + name + "%");
    }
}
