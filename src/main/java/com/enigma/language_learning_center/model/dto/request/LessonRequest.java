package com.enigma.language_learning_center.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {
    private String id;
    private String title;
    private String description;
    private Integer capacity;
    private Long price;
    private String teacherId;
}
