package com.enigma.language_learning_center.controller;

import com.enigma.language_learning_center.model.dto.request.StudentRequest;
import com.enigma.language_learning_center.model.dto.response.CommonResponse;
import com.enigma.language_learning_center.model.dto.response.StudentResponse;
import com.enigma.language_learning_center.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> responses = studentService.getAll();
        CommonResponse<List<StudentResponse>> commonResponse = CommonResponse.<List<StudentResponse>>builder()
                .message("Successfully retrieved all students")
                .data(Optional.of(responses))
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<StudentResponse>> getStudentById(@PathVariable String id) {
        StudentResponse response = studentService.getById(id);
        CommonResponse<StudentResponse> commonResponse = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Student retrieved successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<StudentResponse>> createStudent(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.create(request);
        CommonResponse<StudentResponse> commonResponse = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Student created successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<StudentResponse>> updateStudent(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.update(request);
        CommonResponse<StudentResponse> commonResponse = CommonResponse.<StudentResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(response))
                .message("Student created successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteStudent(@PathVariable String id ) {
        studentService.delete(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Student deleted successfully")
                .data(Optional.empty())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
