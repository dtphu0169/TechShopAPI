package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.dto.CategoryDto;
import com.tech.TechShopAPI.dto.FeedbackDto;
import com.tech.TechShopAPI.dto.ProductDto;
import com.tech.TechShopAPI.model.Label;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.response.PaginationResponse;
import com.tech.TechShopAPI.repository.CategoryRepository;
import com.tech.TechShopAPI.repository.LabelRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import com.tech.TechShopAPI.service.CategoryService;
import com.tech.TechShopAPI.service.FeedbackService;
import com.tech.TechShopAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    FeedbackService feedbackService;

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
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return new ResponseEntity<List<ProductDto>>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/available_products")
    public ResponseEntity<List<ProductDto>> getAvailableProduct(){
        return new ResponseEntity<List<ProductDto>>(productService.findAllAvailable(), HttpStatus.OK);
    }

    @GetMapping("/pagin_products")
    public ResponseEntity<PaginationResponse<ProductDto>> getPaginProduct(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                       @RequestParam(defaultValue = "3") Integer pageSize,
                                                                       @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<PaginationResponse<ProductDto>>(productService.findAllWithPagination(pageNo,pageSize,sortBy), HttpStatus.OK);
    }

    @GetMapping("/productId/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int id){
        ProductDto product = productService.findById(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable String categoryName){
        List<ProductDto> products = categoryService.getProductByCategory(categoryName);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
    }
    @GetMapping("/searchProduct/{name}")
    public ResponseEntity<List<ProductDto>> searchProduct(@PathVariable String name){
        List<ProductDto> products = productService.searchByName(name);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories = categoryService.getAllCategory();
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
    }

    @PostMapping("/feedback")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FeedbackDto> addFeedback(Principal principal, @RequestBody FeedbackDto feedbackDto){
        feedbackService.save(principal,feedbackDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //admin
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto product){
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        System.out.println("delete id: "+id);
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody ProductDto product){
        try{
            productService.editProduct(product);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
