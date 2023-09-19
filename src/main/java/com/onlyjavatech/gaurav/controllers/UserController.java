package com.onlyjavatech.gaurav.controllers;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.Entites.User;
import com.onlyjavatech.gaurav.dao.UserRepository;
import com.onlyjavatech.gaurav.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Component
@RequestMapping("/userServices")
public class UserController {

    private final UserRepository userRepository;
    private final UserServices userServices;

    @Autowired
    public UserController(UserRepository userRepository, UserServices userServices) {
        this.userRepository = userRepository;
        this.userServices = userServices;
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        return userServices.getUser(id);
    }

    @GetMapping(value = "/getUserTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable("id") int id){
        return userServices.getUserTransactions(id);
    }

    @GetMapping(value = "/getUserCreditTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserCreditTransactions(@PathVariable("id") int id){
        return userServices.getUserCreditTransactions(id);
    }

    @GetMapping(value = "/getUserDebitTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserDebitTransactions(@PathVariable("id") int id){
        return userServices.getUserDebitTransactions(id);
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return userServices.addUser(user);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user){
        return userServices.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        return userServices.deleteUser(id);
    }

    @PostMapping("/addTransaction/{id}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable("id") int id,@RequestBody Transaction transaction){
        return userServices.addTransaction(transaction, id);
    }

}
