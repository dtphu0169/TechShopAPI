package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.dto.ProductDto;
import com.tech.TechShopAPI.model.Category;
import com.tech.TechShopAPI.model.Label;
import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.response.PaginationResponse;
import com.tech.TechShopAPI.repository.CategoryRepository;
import com.tech.TechShopAPI.repository.LabelRepository;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LabelRepository labelRepository;

    @Override
    public void saveProduct(ProductDto product) {
        Product productData = Dtomapper.remapProduct(product);

        Optional<Label> label = labelRepository.findNewLabel();
        if (!label.isEmpty()){
            productData.setLabel(label.get());
        }
        Optional<Category> category = categoryRepository.findbyName(product.getCategory());
        if (!category.isEmpty()){
            productData.setCategory(category.get());
        }
        productRepository.save(productData);
    }

    @Override
    public void editProduct(ProductDto product) {Product productData = productRepository.findById(product.getId()).get();
        productData.setName(product.getName());
        productData.setActive(product.isActive());
        productData.setDecription(product.getDecription());

        productData.setPrice(product.getPrice());
        productData.setQuantity(product.getQuantity());

        if (product.getLabel() != null){
            Optional<Label> label = labelRepository.findById(product.getLabel().getId());
            productData.setLabel(label.get());
        }


        Optional<Category> category = categoryRepository.findbyName(product.getCategory());
        if (!category.isEmpty()){
            productData.setCategory(category.get());
        }
        productRepository.save(productData);
    }

    @Override
    public List<ProductDto> findAllAvailable() {
        List<ProductDto> list = Dtomapper.mapProductList(productRepository.findAllAvailable());
        Collections.reverse(list);
        return list;
    }

    @Override
    public PaginationResponse<ProductDto> findAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
        PaginationResponse<ProductDto> response = new PaginationResponse<>();
        response.setPer_page(pageSize);
        response.setCurrent_page(pageNo);

        Pageable paging = PageRequest.of(pageNo-1, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAll(paging);
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        if(pagedResult.hasContent()) {
            productDtoList = Dtomapper.mapProductList(pagedResult.getContent());
        }
        response.setData(productDtoList);

        response.setCount(pagedResult.getNumberOfElements());
        response.setTotal((int) pagedResult.getTotalElements());
        response.setLast_visible_page(pagedResult.getTotalPages());
        response.setHas_next_page(pagedResult.hasNext());

        return response;
    }

    @Override
    public List<ProductDto> findAll() {
        return Dtomapper.mapProductList(productRepository.findAll());
    }

    @Override
    public ProductDto findById(int id) {
        Optional<Product> productdata = productRepository.findById(id);
        if (productdata.isEmpty()){
            return null;
        }
        return Dtomapper.mapProduct(productdata.get());
    }

    @Override
    public List<ProductDto> searchByName(String name) {
        List<Product> productList = productRepository.searchByName(name);
        return Dtomapper.mapProductList(productList);
    }
}
