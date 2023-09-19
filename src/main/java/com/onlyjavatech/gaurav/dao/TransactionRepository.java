package com.onlyjavatech.gaurav.dao;

import com.onlyjavatech.gaurav.Entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Transaction t where t.t_id =:t_id")
    public void deleteTransactionByT_id(@Param("t_id") int id);

}
