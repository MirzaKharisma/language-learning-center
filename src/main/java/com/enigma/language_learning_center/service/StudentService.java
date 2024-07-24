package com.enigma.language_learning_center.service;

import com.enigma.language_learning_center.model.dto.request.StudentRequest;
import com.enigma.language_learning_center.model.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentResponse getById(String id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(StudentRequest request);
    void delete(String id);
}
