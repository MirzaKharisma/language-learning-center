package com.enigma.language_learning_center.service;

import com.enigma.language_learning_center.model.dto.request.TeacherRequest;
import com.enigma.language_learning_center.model.dto.response.TeacherResponse;
import com.enigma.language_learning_center.model.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAll();
    TeacherResponse getById(String id);
    Teacher getByIdToTransactional(String id);
    TeacherResponse create(TeacherRequest request);
    TeacherResponse update(TeacherRequest request);
    void delete(String id);
}
