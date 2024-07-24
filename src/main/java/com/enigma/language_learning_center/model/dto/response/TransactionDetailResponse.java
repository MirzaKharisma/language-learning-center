package com.enigma.language_learning_center.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailResponse {
    private String id;
    private LessonResponse lesson;
    private Long lessonPrice;
}
