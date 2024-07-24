package com.enigma.language_learning_center.controller;

import com.enigma.language_learning_center.model.dto.request.TeacherRequest;
import com.enigma.language_learning_center.model.dto.response.CommonResponse;
import com.enigma.language_learning_center.model.dto.response.TeacherResponse;
import com.enigma.language_learning_center.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<TeacherResponse>>> getAllTeachers() {
        List<TeacherResponse> responses = teacherService.getAll();
        CommonResponse<List<TeacherResponse>> commonResponse = CommonResponse.<List<TeacherResponse>>builder()
                .message("Successfully retrieved all teachers")
                .data(Optional.of(responses))
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TeacherResponse>> getTeacherById(@PathVariable String id) {
        TeacherResponse response = teacherService.getById(id);
        CommonResponse<TeacherResponse> commonResponse = CommonResponse.<TeacherResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Teacher retrieved successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<TeacherResponse>> createTeacher(@RequestBody TeacherRequest request) {
        TeacherResponse response = teacherService.create(request);
        CommonResponse<TeacherResponse> commonResponse = CommonResponse.<TeacherResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Teacher created successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TeacherResponse>> updateTeacher(@RequestBody TeacherRequest request) {
        TeacherResponse response = teacherService.update(request);
        CommonResponse<TeacherResponse> commonResponse = CommonResponse.<TeacherResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Teacher updated successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteStudent(@PathVariable String id ) {
        teacherService.delete(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Teacher deleted successfully")
                .data(Optional.empty())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
