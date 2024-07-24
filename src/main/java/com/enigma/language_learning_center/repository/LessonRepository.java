package com.enigma.language_learning_center.repository;

import com.enigma.language_learning_center.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository{
    List<Lesson> findAll();
    Optional<Lesson> findById(String id);
    Lesson saveAndFlush(Lesson lesson);
    void delete(Lesson lesson);
}
