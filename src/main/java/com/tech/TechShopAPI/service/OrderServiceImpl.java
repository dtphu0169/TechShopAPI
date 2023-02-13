package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.CartproductDto;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.model.Bill_detail;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.request.OrderRequest;
import com.tech.TechShopAPI.payload.response.OrderDetailResponse;
import com.tech.TechShopAPI.payload.response.OrderResponse;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.repository.BillRepository;
import com.tech.TechShopAPI.repository.Bill_detailRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    BillRepository billRepository;
    @Autowired
    Bill_detailRepository bill_detailRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;

    private  List<OrderResponse> changebilltoResponse(List<Bill> bills){
        List<OrderResponse> result = new ArrayList<>();
        for(Bill bill : bills){
            OrderResponse response = new OrderResponse();
            response.setId(bill.getId());
            response.setCustomerName(bill.getAccount().getUserName());
            response.setDatecreate(bill.getDatecreate());
            response.setPrice(bill.getPrice());
            response.setShipprice(bill.getShipprice());
            response.setAddress(bill.getAddress());
            response.setPhone(bill.getPhone());
            response.setPaid(bill.isPaid());
            response.setStatus(bill.getStatus());
            response.setNote(bill.getNote());

            List<Bill_detail> billDetails = bill_detailRepository.findByBillId(bill.getId());
            List<OrderDetailResponse> detailResponses = new ArrayList<>();
            for (Bill_detail bd : billDetails){
                OrderDetailResponse detailResponse = new OrderDetailResponse();
                detailResponse.setQuantity(bd.getQuantity());
                detailResponse.setProductName(bd.getProduct().getName());
                detailResponse.setUnit_price(bd.getProduct().getPrice());
                detailResponses.add(detailResponse);
            }
            response.setBillDetails(detailResponses);
            result.add(response);
        }

        return result;
    }

    @Override
    public void createOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        java.sql.Date now = new Date(System.currentTimeMillis());
        List<CartproductDto> details = orderRequest.getDetails();
        List<Bill_detail> billDetails = new ArrayList<>();

        for (CartproductDto dto: details) {
            Product product = productRepository.findById(dto.getProductId()).get();
            Bill_detail billDetail = new Bill_detail();
            billDetail.setProduct(product);
            billDetail.setUnit_price(product.getPrice());
            billDetail.setQuantity(dto.getQuantity());
            billDetails.add(billDetail);
        }

        Bill order = new Bill();
        order.setAccount(account);
        order.setDatecreate(now);
        order.setPrice(orderRequest.getPrice());
        order.setShipprice(orderRequest.getShipprice());
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        order.setPaid(orderRequest.isPaid());
        order.setStatus("PACKING");
        order.setNote(orderRequest.getNote());
//        order.setBillDetails(billDetails);

        order = billRepository.save(order);
        for (Bill_detail detail : billDetails) {
            detail.setBill(order);
            bill_detailRepository.save(detail);
        }
    }

    @Override
    public List<OrderResponse> getHistory(Principal principal) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        List<Bill> bills = billRepository.findByAccountEmail(account.getEmail());

        List<OrderResponse> result = new ArrayList<>();
        for(Bill bill : bills){
            OrderResponse response = new OrderResponse();
            response.setId(bill.getId());
            response.setCustomerName(bill.getAccount().getUserName());
            response.setDatecreate(bill.getDatecreate());
            response.setPrice(bill.getPrice());
            response.setShipprice(bill.getShipprice());
            response.setAddress(bill.getAddress());
            response.setPhone(bill.getPhone());
            response.setPaid(bill.isPaid());
            response.setStatus(bill.getStatus());
            response.setNote(bill.getNote());

            List<Bill_detail> billDetails = bill_detailRepository.findByBillId(bill.getId());
            List<OrderDetailResponse> detailResponses = new ArrayList<>();
            for (Bill_detail bd : billDetails){
                OrderDetailResponse detailResponse = new OrderDetailResponse();
                detailResponse.setQuantity(bd.getQuantity());
                detailResponse.setProductName(bd.getProduct().getName());
                detailResponse.setUnit_price(bd.getProduct().getPrice());
                detailResponses.add(detailResponse);
            }
            response.setBillDetails(detailResponses);
            result.add(response);
        }

        return result;
    }

    @Override
    public OrderResponse getById(int id, Principal principal) throws AuthenticationException {

        Account account = accountRepository.getbyEmail(principal.getName()).get();
        Bill bill = billRepository.findById(id).get();

        if (!(account.getRole().equals("ROLE_ADMIN") || bill.getAccount().getId() == account.getId())){
            throw new AuthenticationException("You do not have access");
        }

        OrderResponse response = new OrderResponse();
        response.setId(bill.getId());
        response.setCustomerName(bill.getAccount().getUserName());
        response.setDatecreate(bill.getDatecreate());
        response.setPrice(bill.getPrice());
        response.setShipprice(bill.getShipprice());
        response.setAddress(bill.getAddress());
        response.setPhone(bill.getPhone());
        response.setPaid(bill.isPaid());
        response.setStatus(bill.getStatus());
        response.setNote(bill.getNote());

        List<Bill_detail> billDetails = bill_detailRepository.findByBillId(bill.getId());
        List<OrderDetailResponse> detailResponses = new ArrayList<>();
        for (Bill_detail bd : billDetails){
            OrderDetailResponse detailResponse = new OrderDetailResponse();
            detailResponse.setQuantity(bd.getQuantity());
            detailResponse.setProductName(bd.getProduct().getName());
            detailResponse.setUnit_price(bd.getProduct().getPrice());
            detailResponses.add(detailResponse);
        }
        response.setBillDetails(detailResponses);

        return response;

    }

    @Override
    public List<OrderResponse> getALL(Principal principal) throws AuthenticationException {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
//        if(!account.getRole().equals("ROLE_ADMIN")){
//            throw new AuthenticationException("You do not have access");
//        }

        List<Bill> bills = billRepository.findAll();
        List<OrderResponse> result = changebilltoResponse(bills);
        return result;
    }

    @Override
    public void editOrder(int id, String status) {
        Bill bill = billRepository.findById(id).get();
        bill.setStatus(status);
        billRepository.save(bill);
    }
}
