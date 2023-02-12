package com.tech.TechShopAPI.repository;

import com.tech.TechShopAPI.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Query("select a.email from Account a where a.id = :id")
    String getEmailbyId(int id);

    @Query("select a from Account a where a.userName = :name")
    Optional<Account> getbyUsername(@Param("name") String userName);

    @Query("select a from Account a where a.email = :email")
    Optional<Account> getbyEmail(@Param("email") String email);


}
