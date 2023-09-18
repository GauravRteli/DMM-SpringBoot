package com.onlyjavatech.gaurav.controllers;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.Entites.User;
import com.onlyjavatech.gaurav.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Component
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.of(Optional.of(users));
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.of(Optional.of(user.get()));
    }

    @GetMapping(value = "/getUserTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.of(Optional.of(user.get().getTransactionList()));
    }

    @GetMapping(value = "/getUserCreditTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserCreditTransactions(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        List<Transaction> transactions = user.get().getTransactionList();
        List<Transaction> creditTransactions = transactions.stream().filter( t -> !t.isDebitorcredit() ).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(creditTransactions));
    }

    @GetMapping(value = "/getUserDebitTransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserDebitTransactions(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        List<Transaction> transactions = user.get().getTransactionList();
        List<Transaction> debitTransactions = transactions.stream().filter( t -> t.isDebitorcredit() ).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(debitTransactions));
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User u = userRepository.save(user);
        return ResponseEntity.of(Optional.of(u));
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user){
        Optional<User> u = userRepository.findById(id);
        if(!u.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        u.get().setName(user.getName());
        u.get().setCity(user.getCity());
        u.get().setStatus(user.getStatus());
        User updatedUser = userRepository.save(u.get());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){

        if(!userRepository.findById(id).isPresent()) return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body("User not Found !");
        userRepository.deleteById(id);
        return ResponseEntity.ok("User Deleted !");

    }

}
