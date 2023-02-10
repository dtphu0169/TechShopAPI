package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.dto.CategoryDto;
import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.Label;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.repository.CategoryRepository;
import com.tech.TechShopAPI.repository.LabelRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import com.tech.TechShopAPI.service.CategoryService;
import com.tech.TechShopAPI.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    LabelRepository labelRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/labels")
    public ResponseEntity<List<Label>> getAllLabel(){
        return new ResponseEntity<List<Label>>(labelRepository.findAll(), HttpStatus.OK);
    }

    //hiện sản phẩm
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/productId/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Optional<Product> productdata = productRepository.findById(id);
        if (productdata.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Product>(productdata.get(), HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable String categoryName){
        List<Product> products = categoryService.getProductByCategory(categoryName);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }
    @GetMapping("/searchProduct/{name}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String name){
        List<Product> products = productRepository.searchByName(name);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories = categoryService.getAllCategory();
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
    }


}
