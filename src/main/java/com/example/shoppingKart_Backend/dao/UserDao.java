package com.example.shoppingKart_Backend.dao;

import com.example.shoppingKart_Backend.model.Product;
import com.example.shoppingKart_Backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<User,Integer> {
    @Query(value = "SELECT `id`, `address`, `email`, `name`, `password`, `phoneno` FROM `userdata` WHERE `email`= :email",nativeQuery = true)
    List<User> searchUser (@Param("email") String email);
}
