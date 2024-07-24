package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.TransactionDetail;
import com.enigma.language_learning_center.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {
    @Override
    public List<TransactionDetail> findAll() {
        return List.of();
    }

    @Override
    public Optional<TransactionDetail> findById(String id) {
        return Optional.empty();
    }

    @Override
    public TransactionDetail save(TransactionDetail lesson) {
        return null;
    }

    @Override
    public void delete(TransactionDetail lesson) {

    }
}
