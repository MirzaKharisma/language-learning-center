package com.enigma.language_learning_center.model.dto.response;

import com.enigma.language_learning_center.model.entity.Student;
import com.enigma.language_learning_center.model.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private Student student;
    private Long transactionTime;
    private List<TransactionDetail> transactionDetails;
    private Long totalPayment;
}
