package com.enigma.language_learning_center.service.impl;

import com.enigma.language_learning_center.model.dto.request.StudentRequest;
import com.enigma.language_learning_center.model.dto.response.StudentResponse;
import com.enigma.language_learning_center.model.entity.Student;
import com.enigma.language_learning_center.repository.StudentRepository;
import com.enigma.language_learning_center.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.findAll().stream().map(this::convertToStudentResponse).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getById(String id) {
        return convertToStudentResponse(findByIdOrThrow(id));
    }

    @Override
    public Student getByIdToTransaction(String id) {
        return findByIdOrThrow(id);
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        return convertToStudentResponse(studentRepository.saveAndFlush(student));
    }

    @Override
    public StudentResponse update(StudentRequest request) {
        Student student = findByIdOrThrow(request.getId());
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        return convertToStudentResponse(studentRepository.saveAndFlush(student));
    }

    @Override
    public void delete(String id) {
        Student student = findByIdOrThrow(id);
        studentRepository.delete(student);
    }

    private Student findByIdOrThrow(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    private StudentResponse convertToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .email(student.getEmail())
                .phone(student.getPhone())
                .build();
    }
}
