package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Bill_detail;
import com.tech.TechShopAPI.payload.response.OrderDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Bill_detailRepository extends JpaRepository<Bill_detail,Integer> {
    @Query("select bd from Bill_detail bd " +
            "inner join bd.bill b " +
            "where b.id = :id")
    List<Bill_detail> findByBillId(@Param("id") int id);
}
