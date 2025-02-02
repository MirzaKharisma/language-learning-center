package com.enigma.language_learning_center.service;

import com.enigma.language_learning_center.model.dto.request.LessonRequest;
import com.enigma.language_learning_center.model.dto.response.LessonResponse;
import com.enigma.language_learning_center.model.entity.Lesson;

import java.util.List;

public interface LessonService {
    List<LessonResponse> getAll();
    LessonResponse getById(String id);
    Lesson getByIdToTransactional(String id);
    LessonResponse create(LessonRequest request);
    LessonResponse update(LessonRequest request);
    void delete(String id);
}
