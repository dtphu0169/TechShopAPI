package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.model.Cartproduct;
import com.tech.TechShopAPI.repository.BillRepository;
import com.tech.TechShopAPI.repository.CartproductRepository;
import com.tech.TechShopAPI.repository.CategoryRepository;
import com.tech.TechShopAPI.service.CartproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    CartproductService cartproductService;

    @Autowired
    BillRepository billRepository;
    @Autowired
    CartproductRepository cartproductRepository;

    //test entity
    @GetMapping("bills")
    public ResponseEntity<List<Bill>> getAllbill(){
        return new ResponseEntity<List<Bill>>(billRepository.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("Allcartproducts")
    public ResponseEntity<List<Cartproduct>> getAllcartproduct(){
        return new ResponseEntity<List<Cartproduct>>(cartproductRepository.findAll(), HttpStatus.OK);
    }

    //gio hang
    @GetMapping("cartproducts")
    public ResponseEntity<List<Cartproduct>> getcartproduct(Principal principal){
        return new ResponseEntity<List<Cartproduct>>(cartproductService.getcartproduct(principal), HttpStatus.OK);
    }
}
