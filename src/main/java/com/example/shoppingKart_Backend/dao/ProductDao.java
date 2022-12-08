package com.example.shoppingKart_Backend.dao;

import com.example.shoppingKart_Backend.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends CrudRepository<Product,Integer> {


    @Query(value = "SELECT `id`, `category`, `description`, `image`, `name`, `price` FROM `products` WHERE `name` LIKE :name% ",nativeQuery = true)
    List<Product> searchProduct (@Param("name") String name);
}
