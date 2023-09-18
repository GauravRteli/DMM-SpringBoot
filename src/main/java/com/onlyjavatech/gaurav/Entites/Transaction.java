package com.onlyjavatech.gaurav.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlyjavatech.gaurav.Entites.User;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int t_id;
    boolean debitorcredit; // true means it is a Debit and false means it is a Credit
    int amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    public Transaction() {
    }

    public Transaction(boolean debitorcredit, int amount, User user) {
        this.debitorcredit = debitorcredit;
        this.amount = amount;
        this.user = user;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public boolean isDebitorcredit() {
        return debitorcredit;
    }

    public void setDebitorcredit(boolean debitorcredit) {
        this.debitorcredit = debitorcredit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "t_id=" + t_id +
                ", debitorcredit=" + debitorcredit +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}
