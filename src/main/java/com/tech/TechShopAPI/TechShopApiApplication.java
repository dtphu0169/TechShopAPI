package com.tech.TechShopAPI;

import com.tech.TechShopAPI.config.RsaKeyProperties;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TechShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechShopApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(AccountRepository users, PasswordEncoder encoder) {
//		return args -> {
//			users.save(new Account(1,"user","000000000","user@account.com","ROLE_USER",true,encoder.encode("password"),new Date(2023,01,30)));
//			users.save(new Account(2,"admin","000000000","admin@account.com","ROLE_ADMIN,ROLE_USER",true,encoder.encode("password"),new Date(2023,01,30)));
//		};
//	}
}
