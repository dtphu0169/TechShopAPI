package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.payload.response.EntityResponse;
import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.payload.request.OrderRequest;
import com.tech.TechShopAPI.payload.response.OrderResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface OrderService {
    public OrderResponse createOrder(OrderRequest orderRequest, Principal principal,boolean prepaid);

    List<OrderResponse> getHistory(Principal principal);

    OrderResponse getById(long id, Principal principal) throws AuthenticationException;

    List<OrderResponse> getALL(Principal principal) throws AuthenticationException;

    void editOrder(long id, String status);

    Bill findOrderByAccountIdAndOrderId(int accountId,long orderId);

    EntityResponse updatePaidOrder(Bill bill);
}
