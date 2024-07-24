package com.enigma.language_learning_center.service.impl;

import com.enigma.language_learning_center.model.dto.request.TeacherRequest;
import com.enigma.language_learning_center.model.dto.response.TeacherResponse;
import com.enigma.language_learning_center.model.entity.Teacher;
import com.enigma.language_learning_center.repository.TeacherRepository;
import com.enigma.language_learning_center.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    @Override
    public List<TeacherResponse> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public TeacherResponse getById(String id) {
        return convertToResponse(findByIdOrThrow(id));
    }

    @Override
    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = Teacher.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        return convertToResponse(teacherRepository.saveAndFlush(teacher));
    }

    @Override
    public TeacherResponse update(TeacherRequest request) {
        Teacher teacher = findByIdOrThrow(request.getId());
        teacher.setName(request.getName());
        teacher.setAddress(request.getAddress());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
        return convertToResponse(teacherRepository.saveAndFlush(teacher));
    }

    @Override
    public void delete(String id) {
        Teacher teacher = findByIdOrThrow(id);
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher getByIdToTransactional(String id) {
        return findByIdOrThrow(id);
    }

    private TeacherResponse convertToResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .address(teacher.getAddress())
                .phone(teacher.getPhone())
                .email(teacher.getEmail())
                .build();
    }

    private Teacher findByIdOrThrow(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }
}
