package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    @Query("select b from Bill b " +
            "inner join b.account a " +
            "where a.email = :email")
    List<Bill> findByAccountEmail(String email);
}
