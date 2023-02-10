package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.CategorySpecifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("select c from Category c where c.name = :name")
    public Optional<Category> findbyName(@Param("name") String name);
}
