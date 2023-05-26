package com.tech.TechShopAPI.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
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

    final String clientId = "369059669510-dmfpbgs8nm110bpojvajkm8neqi28vcc.apps.googleusercontent.com";
    final String clientSecret = "369059669510-dmfpbgs8nm110bpojvajkm8neqi28vcc.apps.googleusercontent.com";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(new NetHttpTransport())
                .setJsonFactory(new JacksonFactory())
                .setClientSecrets(clientId, clientSecret)
                .build();

        return accountRepository.getbyEmail(username)
                .map(AccountSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
