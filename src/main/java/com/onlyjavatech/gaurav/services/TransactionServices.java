package com.onlyjavatech.gaurav.services;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.dao.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServices {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<String> removeTransaction(int id){
        if(transactionRepository.findById(id).isEmpty()) ResponseEntity.status(404).build();
        transactionRepository.deleteTransactionByT_id(id);
        return ResponseEntity.ok("done");
    }

    public ResponseEntity<Transaction> updateTransaction(Transaction transaction, int t_id){
        Optional<Transaction> op = transactionRepository.findById(t_id);
        if(op.isEmpty()) ResponseEntity.status(404).build();

        Transaction existingTransaction = op.get();


        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDebitorcredit(transaction.isDebitorcredit());
        existingTransaction.setDate(transaction.getDate());

        transactionRepository.save(existingTransaction);

        return ResponseEntity.of(Optional.of(existingTransaction));
    }

}
