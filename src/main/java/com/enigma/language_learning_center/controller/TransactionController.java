package com.enigma.language_learning_center.controller;

import com.enigma.language_learning_center.model.dto.request.TransactionRequest;
import com.enigma.language_learning_center.model.dto.response.CommonResponse;
import com.enigma.language_learning_center.model.dto.response.TransactionResponse;
import com.enigma.language_learning_center.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson-transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getAllTransactions() {
        List<TransactionResponse> responses = transactionService.getAll();
        CommonResponse<List<TransactionResponse>> commonResponse = CommonResponse.<List<TransactionResponse>>builder()
                .message("Successfully retrieved all transactions")
                .statusCode(HttpStatus.OK.value())
                .data(Optional.of(responses))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.create(request);
        CommonResponse<TransactionResponse> commonResponse = CommonResponse.<TransactionResponse>builder()
                .data(Optional.of(response))
                .statusCode(HttpStatus.CREATED.value())
                .message("Transaction Was Created Successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }
}
