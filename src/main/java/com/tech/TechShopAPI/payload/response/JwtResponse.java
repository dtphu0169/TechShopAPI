package com.tech.TechShopAPI.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    String token;
    int id;
    String name;
    String email;
    String phone;
    String role;
}
