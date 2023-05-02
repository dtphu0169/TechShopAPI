package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Product;
import com.tech.TechShopAPI.payload.response.PaginationResponse;
import com.tech.TechShopAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void editProduct(Product product) {
        Product productData = productRepository.findById(product.getId()).get();
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllAvailable() {
        return productRepository.findAllAvailable();
    }

    @Override
    public PaginationResponse<Product> findAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
        PaginationResponse<Product> response = new PaginationResponse<>();
        response.setPer_page(pageSize);
        response.setCurrent_page(pageNo);

        Pageable paging = PageRequest.of(pageNo-1, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            response.setData(pagedResult.getContent());
        } else {
            response.setData(new ArrayList<Product>());
        }

        response.setCount(pagedResult.getNumberOfElements());
        response.setTotal((int) pagedResult.getTotalElements());
        response.setLast_visible_page(pagedResult.getTotalPages());
        response.setHas_next_page(pagedResult.hasNext());

        return response;
    }
}
