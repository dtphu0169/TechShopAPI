package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.AccountSecurity;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
     AccountRepository accountRepository;

//    public JpaUserDetailsService(AccountRepository accountRepository){
//        this.accountRepository = accountRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.getbyEmail(username)
                .map(AccountSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
