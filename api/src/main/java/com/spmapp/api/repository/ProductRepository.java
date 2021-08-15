package com.spmapp.api.repository;

import com.github.javafaker.Faker;
import com.spmapp.api.models.Product;
import com.spmapp.api.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productList = new ArrayList();

    Faker faker = new Faker();

    public ProductRepository() {
        for (int i = 0; i < 100; i++) {
            String id = faker.internet().uuid();
            String title = faker.book().title();
            String author = faker.book().author();
            String genre = faker.book().genre();
            String publisher = faker.book().publisher();

            Product product = new Product(id,title, author, genre, publisher);
            productList.add(product);
        }
    }

    public List<Product> findAll() {
        return productList;
    }

    public Product findOne(String id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst().get();
    }

    public Product addProduct(Product product) {
        productList.add(product);
        return product;
    }

    public Product deleteProduct(String id) {
        Product productToDelete = productList.stream().filter(product -> product.getId().equals(id)).findFirst().get();

        productList.remove(productToDelete);
        return  productToDelete;
    }

}
