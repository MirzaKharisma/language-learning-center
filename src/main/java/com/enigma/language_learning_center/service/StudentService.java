package com.enigma.language_learning_center.service;

import com.enigma.language_learning_center.model.dto.request.StudentRequest;
import com.enigma.language_learning_center.model.dto.response.StudentResponse;
import com.enigma.language_learning_center.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentResponse getById(String id);
    Student getByIdToTransaction(String id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(StudentRequest request);
    void delete(String id);
}
