package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label,Integer> {
    @Query("select l from Label l " +
            "where l.name like 'new'")
    Optional<Label> findNewLabel();
}
