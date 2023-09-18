package com.onlyjavatech.gaurav.dao;

import com.onlyjavatech.gaurav.Entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
