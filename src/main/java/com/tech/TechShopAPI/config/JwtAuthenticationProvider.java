//package com.tech.TechShopAPI.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component // để Spring quản lý Bean này
//public class JwtAuthenticationProvider implements AuthenticationProvider {
//
//    private final RsaKeyProperties rsaKeys;
//
//    public JwtAuthenticationProvider(RsaKeyProperties rsaKeys) {
//        this.rsaKeys = rsaKeys;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String token = authentication.getCredentials().toString();
//
//        try {
//            Claims claims = Jwts.parser()
//                    .setSigningKey(getSecretKey())
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            String username = claims.getSubject();
//            List<String> roles = (List<String>) claims.get("roles");
//
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            for (String role : roles) {
//                authorities.add(new SimpleGrantedAuthority(role));
//            }
//
//            return new UsernamePasswordAuthenticationToken(username, null, authorities);
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid token");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//    private Key getSecretKey() {
//        byte[] keyBytes = rsaKeys.privateKey().getEncoded();
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        return new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
//    }
//
//}
