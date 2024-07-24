package com.enigma.language_learning_center.service.impl;

import com.enigma.language_learning_center.model.dto.request.LessonRequest;
import com.enigma.language_learning_center.model.dto.response.LessonResponse;
import com.enigma.language_learning_center.model.entity.Lesson;
import com.enigma.language_learning_center.repository.LessonRepository;
import com.enigma.language_learning_center.service.LessonService;
import com.enigma.language_learning_center.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final TeacherService teacherService;
    private final LessonRepository lessonRepository;

    @Override
    public List<LessonResponse> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map(this::convertLessonToLessonResponse).collect(Collectors.toList());
    }

    @Override
    public LessonResponse getById(String id) {
        return convertLessonToLessonResponse(findLessonByIdOrThrowException(id));
    }

    @Override
    public Lesson getByIdToTransactional(String id) {
        return findLessonByIdOrThrowException(id);
    }

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = Lesson.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .price(request.getPrice())
                .teacher(teacherService.getByIdToTransactional(request.getTeacherId()))
                .build();
        return convertLessonToLessonResponse(lessonRepository.saveAndFlush(lesson));
    }

    @Override
    public LessonResponse update(LessonRequest request) {
        Lesson lesson = findLessonByIdOrThrowException(request.getId());
        lesson.setTitle(request.getTitle());
        lesson.setDescription(request.getDescription());
        lesson.setCapacity(request.getCapacity());
        lesson.setPrice(request.getPrice());
        lesson.setTeacher(teacherService.getByIdToTransactional(request.getTeacherId()));
        return convertLessonToLessonResponse(lessonRepository.saveAndFlush(lesson));
    }

    @Override
    public void delete(String id) {
        Lesson lesson = findLessonByIdOrThrowException(id);
        lessonRepository.delete(lesson);
    }

    private LessonResponse convertLessonToLessonResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .capacity(lesson.getCapacity())
                .description(lesson.getDescription())
                .price(lesson.getPrice())
                .teacher(lesson.getTeacher())
                .build();
    }

    private Lesson findLessonByIdOrThrowException(String id) {
        return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}
