package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Cartproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartproductRepository extends JpaRepository<Cartproduct,Integer> {
    //query goi ten duoc dinh nghia tren code
    @Query("select c from Cartproduct c " +
            "inner join c.account a " +
            "where a.email like :name")
//            "where c.product_id =1002")
    List<Cartproduct> findAllByAccountId(String name);
}
