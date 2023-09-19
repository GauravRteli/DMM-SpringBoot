package com.onlyjavatech.gaurav.services;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.Entites.User;
import com.onlyjavatech.gaurav.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Transaction> getAllTransactionById(int id){

        List<Transaction> transactions = userRepository.findById(id).get().getTransactionList();
        return transactions;

    }

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.of(Optional.of(users));
    }

    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(user.get()));
    }

    public ResponseEntity<List<Transaction>> getUserTransactions(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(404).build();
        return ResponseEntity.of(Optional.of(user.get().getTransactionList()));
    }

    public ResponseEntity<List<Transaction>> getUserCreditTransactions(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(404).build();
        List<Transaction> transactions = user.get().getTransactionList();
        List<Transaction> creditTransactions = transactions.stream().filter( t -> !t.isDebitorcredit() ).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(creditTransactions));
    }

    public ResponseEntity<List<Transaction>> getUserDebitTransactions(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(404).build();
        List<Transaction> transactions = user.get().getTransactionList();
        List<Transaction> debitTransactions = transactions.stream().filter( t -> t.isDebitorcredit() ).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(debitTransactions));
    }

    public ResponseEntity<User> addUser(User user){
        User u = userRepository.save(user);
        return ResponseEntity.of(Optional.of(u));
    }

    public ResponseEntity<Transaction> addTransaction(Transaction transaction, int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return ResponseEntity.status(404).build();
        transaction.setUser(user.get());
        user.get().getTransactionList().add(transaction);
        userRepository.save(user.get());

        return ResponseEntity.of(Optional.of(transaction));
    }

    public ResponseEntity<User> updateUser(int id, User user){
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        u.get().setName(user.getName());
        u.get().setCity(user.getCity());
        u.get().setStatus(user.getStatus());
        User updatedUser = userRepository.save(u.get());
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<String> deleteUser(int id){

        if(!userRepository.findById(id).isPresent()) return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body("User not Found !");
        userRepository.deleteById(id);
        return ResponseEntity.ok("User Deleted !");

    }

}
