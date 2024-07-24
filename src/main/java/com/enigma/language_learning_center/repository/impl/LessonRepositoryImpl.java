package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.Lesson;
import com.enigma.language_learning_center.repository.LessonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Lesson> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_lesson", Lesson.class);
        return query.getResultList();
    }

    @Override
    public Optional<Lesson> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_lesson WHERE id = :id", Lesson.class);
        query.setParameter("id", id);
        List<Lesson> lessons = query.getResultList();
        return lessons.isEmpty() ? Optional.empty() : Optional.of(lessons.get(0));
    }

    @Override
    @Transactional
    public Lesson saveAndFlush(Lesson lesson) {
        Lesson savedLesson;
        if (lesson.getId() == null) {
            entityManager.persist(lesson);
            savedLesson = lesson;
        }else{
            savedLesson = entityManager.merge(lesson);
        }
        entityManager.flush();
        return savedLesson;
    }

    @Override
    @Transactional
    public void delete(Lesson lesson) {
        entityManager.remove(lesson);
    }
}
