package com.enigma.language_learning_center.service.impl;

import com.enigma.language_learning_center.model.dto.request.TransactionRequest;
import com.enigma.language_learning_center.model.dto.response.TransactionResponse;
import com.enigma.language_learning_center.model.entity.Lesson;
import com.enigma.language_learning_center.model.entity.Student;
import com.enigma.language_learning_center.model.entity.Transaction;
import com.enigma.language_learning_center.model.entity.TransactionDetail;
import com.enigma.language_learning_center.repository.TransactionDetailRepository;
import com.enigma.language_learning_center.repository.TransactionRepository;
import com.enigma.language_learning_center.service.LessonService;
import com.enigma.language_learning_center.service.StudentService;
import com.enigma.language_learning_center.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final StudentService studentService;
    private final LessonService lessonService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResponse create(TransactionRequest request) {
        Student student = studentService.getByIdToTransaction(request.getStudentId());
        Transaction transaction = Transaction.builder()
                .student(student)
                .transactionTime(System.currentTimeMillis())
                .build();

        AtomicReference<Long> totalPayment = new AtomicReference<>(0L);

        List<TransactionDetail> transactionDetails = request.getTransactionDetails().stream().map(detailRequest -> {
            Lesson lesson = lessonService.getByIdToTransactional(detailRequest.getLessonId());
            if(lesson.getCapacity() - 1 < 0){
                throw new RuntimeException("Lesson capacity exceeded");
            }

            lesson.setCapacity(lesson.getCapacity()-1);

            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .lesson(lesson)
                    .transaction(transaction)
                    .lessonPrice(lesson.getPrice())
                    .build();

            totalPayment.updateAndGet(cur -> cur + lesson.getPrice());

            transactionDetailRepository.save(transactionDetail);
            return transactionDetail;
        }).collect(Collectors.toList());

        transaction.setTransactionDetails(transactionDetails);
        transactionRepository.saveAndFlush(transaction);
        return convertTransactionToResponse(transaction, totalPayment.get());
    }

    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transaction -> convertTransactionToResponse(transaction, calculateTotalPayment())).toList();
    }

    private Long calculateTotalPayment() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .flatMap(transaction -> transaction.getTransactionDetails().stream())
                .mapToLong(TransactionDetail::getLessonPrice)
                .sum();
    }

    private TransactionResponse convertTransactionToResponse(Transaction transaction, Long totalPayment) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .student(transaction.getStudent())
                .transactionTime(transaction.getTransactionTime())
                .transactionDetails(transaction.getTransactionDetails())
                .totalPayment(totalPayment)
                .build();
    }
}
