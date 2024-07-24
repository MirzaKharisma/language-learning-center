package com.enigma.language_learning_center.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<T> data;
}
