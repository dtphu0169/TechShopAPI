package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Integer> {
}
