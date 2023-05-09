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

//    @Query(value = "select * from Product where name like %:name%",nativeQuery = true)
    @Query("select p from Product p where name like %:name%")
    List<Product> searchByName(@Param("name") String name);

    @Query("select p from Product p " +
            "where p.active = true and " +
            "p.quantity >= 1")
    List<Product> findAllAvailable();

    @Query("select p from Product p " +
            "where p.name like :name")
    Optional<Product> findByName(String name);
}
