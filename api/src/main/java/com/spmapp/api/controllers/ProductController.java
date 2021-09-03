package com.spmapp.api.controllers;

import com.spmapp.api.models.Product;
import com.spmapp.api.models.Response;
import com.spmapp.api.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }




    @GetMapping("/products")
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @GetMapping("/products/{id}")
    public Product findOne(@PathVariable String id) {
        return productRepository.findOne(id);
    }


    @PostMapping("/products")
    public Response<Product> addProduct(@RequestBody Product product) {
        return productRepository.addProduct(product);
    }


    @PutMapping("/products")
    public Response<Product> updateProduct(@RequestBody Product product) {
        return productRepository.updateProduct(product);
    }


    @DeleteMapping("/products/{id}")
    public Response<Product> deleteProduct(@PathVariable String id) {
        return productRepository.deleteProduct(id);
    }
}
