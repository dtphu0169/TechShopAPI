package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.dto.CartproductDto;
import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.model.Bill_detail;
import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.payload.request.OrderRequest;
import com.tech.TechShopAPI.payload.response.OrderResponse;
import com.tech.TechShopAPI.repository.BillRepository;
import com.tech.TechShopAPI.repository.Bill_detailRepository;
import com.tech.TechShopAPI.repository.CartproductRepository;
import com.tech.TechShopAPI.repository.CategoryRepository;
import com.tech.TechShopAPI.service.CartproductService;
import com.tech.TechShopAPI.service.OrderService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    CartproductService cartproductService;
    @Autowired
    OrderService orderService;

    @Autowired
    BillRepository billRepository;
    @Autowired
    Bill_detailRepository bill_detailRepository;
    @Autowired
    CartproductRepository cartproductRepository;

    //test entity
    @GetMapping("Entity/bills")
    public ResponseEntity<List<Bill>> getAllbill(){
        return new ResponseEntity<List<Bill>>(billRepository.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("Entity/cartproducts")
    public ResponseEntity<List<Cartproduct>> getAllcartproduct(){
        return new ResponseEntity<List<Cartproduct>>(cartproductRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("Entity/billDetails")
    public ResponseEntity<List<Bill_detail>> getAllbillDetails(){
        return new ResponseEntity<List<Bill_detail>>(bill_detailRepository.findAll(), HttpStatus.OK);
    }

    //gio hang
    @GetMapping("/cartproducts")
    public ResponseEntity<List<Cartproduct>> getcartproduct(Principal principal){
        return new ResponseEntity<List<Cartproduct>>(cartproductService.getcartproduct(principal), HttpStatus.OK);
    }
    @PostMapping("/cartproduct")
    public ResponseEntity<?> addProductCart(@RequestBody CartproductDto cartproductDto,Principal principal){
        return new ResponseEntity<Cartproduct>(cartproductService.save(principal,cartproductDto),HttpStatus.OK);
    }
    @DeleteMapping("/cartproduct")
    public ResponseEntity<?> deleteProductCart(@RequestBody CartproductDto cartproductDto,Principal principal){
        return new ResponseEntity<>(cartproductService.delete(principal,cartproductDto),HttpStatus.OK);
    }

    //order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest,Principal principal){
        orderService.createOrder(orderRequest,principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<?> orderHistory(Principal principal){
        List<OrderResponse> bills = orderService.getHistory(principal);
        if (bills.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OrderResponse>>(bills,HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetails(@PathVariable int id,Principal principal){
        OrderResponse response = null;
        try {
            response = orderService.getById(id,principal);
            if (response == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<OrderResponse>(response,HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //admin
    @GetMapping("/orders")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrder(Principal principal){
        try {
            List<OrderResponse> response = orderService.getALL(principal);
            if (response.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<OrderResponse>>(response,HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editOrder(@PathVariable int id,@PathVariable String status){
        orderService.editOrder(id,status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
