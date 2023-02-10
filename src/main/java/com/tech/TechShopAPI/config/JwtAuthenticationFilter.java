//package com.tech.TechShopAPI.config;
//
//import com.sun.istack.NotNull;
//import com.tech.TechShopAPI.model.AccountSecurity;
//import com.tech.TechShopAPI.service.JpaUserDetailsService;
//import com.tech.TechShopAPI.service.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final TokenService jwtService;
//    private final JpaUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            @NotNull HttpServletRequest request
//            ,@NotNull HttpServletResponse response
//            ,@NotNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//        jwt = authHeader.substring(7);// "Bearer "
//        userEmail = jwtService.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() ==null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            if (jwtService.isTokenValid(jwt,userDetails)){
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
////        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
////        jwtService.isTokenValid(jwt,userDetails);
//        filterChain.doFilter(request, response);
//    }
//}
