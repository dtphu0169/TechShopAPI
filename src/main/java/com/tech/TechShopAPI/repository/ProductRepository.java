package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from Product where name like %:name%",nativeQuery = true)
    List<Product> searchByName(@Param("name") String name);


}
