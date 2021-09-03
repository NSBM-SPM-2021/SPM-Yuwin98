package com.spmapp.api.controllers;

import com.spmapp.api.ApiApplication;
import com.spmapp.api.models.Product;
import com.spmapp.api.models.Response;
import com.spmapp.api.models.UserResponse;
import com.spmapp.api.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ApiApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {


    @Autowired
    ProductController productController;


    @Test
    @Order(1)
    void addProduct() {
        Product p = new Product();
        p.setId("123");
        p.setTitle("Test Product");
        p.setGenre("Test Genre");
        p.setAuthor("Test Author");
        p.setPublisher("Test Publisher");

        Response<Product> response = productController.addProduct(p);

        assertEquals("Product created", response.getMessage());
        assertEquals(201, (long)response.getStatusCode());
        assertEquals("123", response.getData().get().getId());

    }

    @Test
    @Order(2)
    void findOne() {
        Product p = productController.findOne("123");
        assertEquals("Test Product", p.getTitle());
        assertEquals("Test Genre", p.getGenre());
        assertEquals("Test Author", p.getAuthor());
        assertEquals("Test Publisher", p.getPublisher());
    }

    @Test
    @Order(3)
    void updateProduct() {
        Product p = productController.findOne("123");
        p.setTitle("Test Product Updated");

        Response<Product> response = productController.updateProduct(p);

        assertEquals("Product updated", response.getMessage());
        assertEquals(200, (long)response.getStatusCode());
        assertEquals("Test Product Updated", response.getData().get().getTitle());

    }

    @Test
    @Order(4)
    void deleteProduct() {
        Response<Product> response = productController.deleteProduct("123");

        assertEquals("Product deleted successfully", response.getMessage());
        assertEquals(200, (long)response.getStatusCode());
    }
}