package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.Transaction;
import com.enigma.language_learning_center.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Transaction> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_transaction", Transaction.class);
        return query.getResultList();
    }

    @Override
    public Optional<Transaction> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_transaction WHERE id = :id", Transaction.class);
        query.setParameter("id", id);
        List<Transaction> transactions = query.getResultList();
        return transactions.isEmpty() ? Optional.empty() : Optional.of(transactions.get(0));
    }

    @Override
    @Transactional
    public Transaction saveAndFlush(Transaction transaction) {
        Transaction savedTransaction;
        if (transaction.getId() == null) {
            entityManager.persist(transaction);
            savedTransaction = transaction;
        }else{
            savedTransaction = entityManager.merge(transaction);
        }
        entityManager.flush();
        return savedTransaction;
    }

    @Override
    @Transactional
    public void delete(Transaction transaction) {
        entityManager.remove(transaction);
    }
}
