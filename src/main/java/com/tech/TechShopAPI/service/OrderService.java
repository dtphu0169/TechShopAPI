package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.payload.request.OrderRequest;
import com.tech.TechShopAPI.payload.response.OrderResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface OrderService {
    public void createOrder(OrderRequest orderRequest, Principal principal);

    List<OrderResponse> getHistory(Principal principal);

    OrderResponse getById(int id, Principal principal) throws AuthenticationException;

    List<OrderResponse> getALL(Principal principal) throws AuthenticationException;

    void editOrder(int id, String status);
}
