package com.enigma.language_learning_center.controller;

import com.enigma.language_learning_center.model.dto.request.LessonRequest;
import com.enigma.language_learning_center.model.dto.response.CommonResponse;
import com.enigma.language_learning_center.model.dto.response.LessonResponse;
import com.enigma.language_learning_center.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<LessonResponse>>> getAllLessons() {
        List<LessonResponse> responses = lessonService.getAll();
        CommonResponse<List<LessonResponse>> commonResponse = CommonResponse.<List<LessonResponse>>builder()
                .message("Successfully retrieved all lessons")
                .data(Optional.of(responses))
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LessonResponse>> getLessonById(@PathVariable String id) {
        LessonResponse response = lessonService.getById(id);
        CommonResponse<LessonResponse> commonResponse = CommonResponse.<LessonResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Lesson retrieved successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<LessonResponse>> createLesson(@RequestBody LessonRequest request) {
        LessonResponse response = lessonService.create(request);
        CommonResponse<LessonResponse> commonResponse = CommonResponse.<LessonResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Lesson created successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<LessonResponse>> updateLesson(@RequestBody LessonRequest request) {
        LessonResponse response = lessonService.update(request);
        CommonResponse<LessonResponse> commonResponse = CommonResponse.<LessonResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Lesson created successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteStudent(@PathVariable String id ) {
        lessonService.delete(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Student deleted successfully")
                .data(Optional.empty())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
