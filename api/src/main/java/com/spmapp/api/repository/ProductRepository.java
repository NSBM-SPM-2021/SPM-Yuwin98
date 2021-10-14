package com.spmapp.api.repository;

import com.github.javafaker.Faker;
import com.spmapp.api.models.Product;
import com.spmapp.api.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {


    public static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getString("product_id"));
        product.setTitle(rs.getString("title"));
        product.setAuthor(rs.getString("author"));
        product.setGenre(rs.getString("genre"));
        product.setPublisher(rs.getString("publisher"));

        return product;
    };

    public ProductRepository() {

    }

    public List<Product> findAll() {
        String sql = "Select product_id, title, author, genre, publisher from product ORDER BY id DESC";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public Product findOne(String id) {
        String sql = "Select product_id, title, author, genre, publisher from product where product_id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, id);
    }

    public Response<Product> addProduct(Product product) {
        Response<Product> response = new Response<>();
        String sql = "Insert into product(product_id,title, author, genre, publisher) values(?,?,?,?,?)";

        int insert = jdbcTemplate.update(sql, product.getId(), product.getTitle(), product.getAuthor(), product.getGenre(), product.getPublisher());

        if (insert == 1) {
            response.setMessage("Product created");
            response.setStatusCode(201);
            response.setData(Optional.of(product));
            return response;
        }
        response.setMessage("Product creation failed");
        response.setStatusCode(500);
        response.setData(Optional.empty());
        return response;
    }

    public Response<Product> updateProduct(Product product) {
        Response<Product> response = new Response<>();
        String sql = "update product set title = ?, author = ?, genre = ? , publisher = ? where product_id = ?";

        int update = jdbcTemplate.update(sql, product.getTitle(), product.getAuthor(), product.getGenre(), product.getPublisher(), product.getId());

        if (update == 1) {
            response.setMessage("Product updated");
            response.setStatusCode(200);
            response.setData(Optional.of(product));
            return response;
        }
        response.setMessage("Product update failed");
        response.setStatusCode(500);
        return response;
    }

    public Response<Product> deleteProduct(String id) {
        Response<Product> response = new Response<>();
        String sql = "Delete from product where product_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            response.setMessage("Product deleted successfully");
            response.setStatusCode(200);
            return response;
        }

        response.setMessage("Delete failed");
        response.setStatusCode(500);
        return response;
    }

}
