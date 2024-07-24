package com.enigma.language_learning_center.model.dto.response;

import com.enigma.language_learning_center.model.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {
    private String id;
    private String title;
    private String description;
    private Integer capacity;
    private Long price;
    private Teacher teacher;
}
