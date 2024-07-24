package com.enigma.language_learning_center.repository;

import com.enigma.language_learning_center.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository{
    List<Student> findAll();
    Optional<Student> findById(String id);
    Student saveAndFlush(Student lesson);
    void delete(Student lesson);
}
