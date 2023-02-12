package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Cartproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartproductRepository extends JpaRepository<Cartproduct,Integer> {
    //query goi ten duoc dinh nghia tren code
    @Query("select c from Cartproduct c " +
            "inner join c.account a " +
            "where a.email like :name and " +
            "c.quantity != 0")
    List<Cartproduct> findAllByAccountId(String name);

    @Query("select c from Cartproduct c " +
            "inner join c.account a " +
            "inner join c.product p " +
            "where p.id = :productId and " +
            "a.id = :accountId")
    Optional<Cartproduct> findInCart(@Param("productId") int productId, @Param("accountId") int accountId);
}
