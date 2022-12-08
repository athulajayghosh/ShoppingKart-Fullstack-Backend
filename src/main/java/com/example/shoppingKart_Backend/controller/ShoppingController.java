package com.example.shoppingKart_Backend.controller;


import com.example.shoppingKart_Backend.dao.ProductDao;
import com.example.shoppingKart_Backend.dao.UserDao;
import com.example.shoppingKart_Backend.model.Product;
import com.example.shoppingKart_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class ShoppingController {
    @Autowired
    private UserDao dao;
    @Autowired
    private ProductDao pdao;

    @GetMapping("/")
    public String homepage() {
        return "Page Working fine";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewuser")
    public List<User> viewuser() {
        return (List<User>) dao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adduser", consumes = "application/json", produces = "application/json")
    public User adduser(@RequestBody User u) {
        dao.save(u);
        return u;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userlogin", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> Userlogin(@RequestBody User u) {
        System.out.println(u.getEmail());
        List<User> result = (List<User>) dao.searchUser(u.getEmail());
        HashMap<String,String> map = new HashMap<>();
        if(result.size()==0){
            map.put("status","failed");
            map.put("message","User doesn't exist");
        }
        else{
            if(Objects.equals(result.get(0).getPassword(), u.getPassword())){
                map.put("status","success");
                map.put("message","User login success");
            }
            else {
                map.put("status","failed");
                map.put("message","Wrong password");
            }
        }
        return map;
    }

    //---------------------------------------------------------------------------------------------------------------
    @CrossOrigin(origins = "*")
    @GetMapping("/viewproduct")
    public List<Product> viewproduct() {

        return (List<Product>) pdao.findAll();
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addproduct", consumes = "application/json", produces = "application/json")
    public Product adduser(@RequestBody Product p) {
        pdao.save(p);
        return p;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchproduct", consumes = "application/json", produces = "application/json")
    public List<Product> searchproduct(@RequestBody Product p) {
        System.out.println(p.getName());
        return (List<Product>) pdao.searchProduct(p.getName());

    }
}
