package com.enigma.language_learning_center.repository;

import com.enigma.language_learning_center.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository{
    List<Teacher> findAll();
    Optional<Teacher> findById(String id);
    Teacher saveAndFlush(Teacher lesson);
    void delete(Teacher lesson);
}
