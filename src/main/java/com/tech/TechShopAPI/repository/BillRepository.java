package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {

    @Query("select b from Bill b " +
            "inner join b.account a " +
            "where a.email = :email")
    List<Bill> findByAccountEmail(String email);

    @Query("select b from Bill b " +
            "inner join b.account a " +
            "where b.id = :orderId " +
            "and a.id = :accountId")
    Optional<Bill> findOrderByAccountIdAndOrderId(int accountId, long orderId);
}
