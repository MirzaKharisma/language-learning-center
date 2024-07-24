package com.enigma.language_learning_center.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
