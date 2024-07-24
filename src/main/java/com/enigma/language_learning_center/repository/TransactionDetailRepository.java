package com.enigma.language_learning_center.repository;

import com.enigma.language_learning_center.model.entity.TransactionDetail;

import java.util.List;
import java.util.Optional;

public interface TransactionDetailRepository{
    List<TransactionDetail> findAll();
    Optional<TransactionDetail> findById(String id);
    TransactionDetail save(TransactionDetail lesson);
    void delete(TransactionDetail lesson);
}
