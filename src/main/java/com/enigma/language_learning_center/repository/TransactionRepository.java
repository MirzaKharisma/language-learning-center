package com.enigma.language_learning_center.repository;


import com.enigma.language_learning_center.model.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository{
    List<Transaction> findAll();
    Optional<Transaction> findById(String id);
    Transaction saveAndFlush(Transaction lesson);
    void delete(Transaction lesson);
}
