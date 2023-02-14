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
import com.tech.TechShopAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

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
    @GetMapping("/available_products")
    public ResponseEntity<List<Product>> getAvailableProduct(){
        return new ResponseEntity<List<Product>>(productService.findAllAvailable(), HttpStatus.OK);
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

    //admin
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody Product product){
        productService.editProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
