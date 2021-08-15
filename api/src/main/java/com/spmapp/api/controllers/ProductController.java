package com.spmapp.api.controllers;

import com.spmapp.api.models.Product;
import com.spmapp.api.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product findOne(@PathVariable String id) {
        return productRepository.findOne(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.addProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable String id) {
        return productRepository.deleteProduct(id);
    }
}
