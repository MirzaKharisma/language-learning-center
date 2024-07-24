package com.enigma.language_learning_center.service;

import com.enigma.language_learning_center.model.dto.request.TransactionRequest;
import com.enigma.language_learning_center.model.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest transaction);
    List<TransactionResponse> getAll();
}
