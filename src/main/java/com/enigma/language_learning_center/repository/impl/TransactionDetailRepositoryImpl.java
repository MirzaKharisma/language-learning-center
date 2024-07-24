package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.TransactionDetail;
import com.enigma.language_learning_center.repository.TransactionDetailRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<TransactionDetail> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_transaction_detail", TransactionDetail.class);
        return query.getResultList();
    }

    @Override
    public Optional<TransactionDetail> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_transaction_detail WHERE id = :id", TransactionDetail.class);
        query.setParameter("id", id);
        List<TransactionDetail> transactionDetails = query.getResultList();
        return transactionDetails.isEmpty() ? Optional.empty() : Optional.of(transactionDetails.get(0));
    }

    @Override
    public TransactionDetail save(TransactionDetail lesson) {
        if (lesson.getId() == null) {
            entityManager.persist(lesson);
            return lesson;
        }else{
            return entityManager.merge(lesson);
        }
    }

    @Override
    public void delete(TransactionDetail lesson) {
        entityManager.remove(lesson);
    }
}
