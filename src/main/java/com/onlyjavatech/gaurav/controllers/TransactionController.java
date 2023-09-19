package com.onlyjavatech.gaurav.controllers;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.dao.TransactionRepository;
import com.onlyjavatech.gaurav.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactionServices")
@Component
public class TransactionController {
        TransactionRepository transactionRepository;
        private final TransactionServices transactionServices;

        @Autowired
        public TransactionController(TransactionRepository transactionRepository, TransactionServices transactionServices) {
                this.transactionRepository = transactionRepository;
                this.transactionServices = transactionServices;
        }

        @DeleteMapping("/removeTransaction/{id}")
        public ResponseEntity<String> removeTransaction(@PathVariable("id") int id) {
                return transactionServices.removeTransaction(id);
        }

        @PutMapping("/updateTransaction/{id}")
        public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable("id") int t_id) {
                return transactionServices.updateTransaction(transaction, t_id);
        }
}
