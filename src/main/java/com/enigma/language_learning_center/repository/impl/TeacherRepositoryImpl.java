package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.Teacher;
import com.enigma.language_learning_center.repository.TeacherRepository;
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
public class TeacherRepositoryImpl implements TeacherRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Teacher> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_teacher", Teacher.class);
        return query.getResultList();
    }

    @Override
    public Optional<Teacher> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_teacher WHERE id = :id", Teacher.class);
        query.setParameter("id", id);
        List<Teacher> teachers = query.getResultList();
        return teachers.isEmpty() ? Optional.empty() : Optional.of(teachers.get(0));
    }

    @Override
    @Transactional
    public Teacher saveAndFlush(Teacher teacher) {
        Teacher savedTeacher;
        if (teacher.getId() == null) {
            entityManager.persist(teacher);
            savedTeacher = teacher;
        }else{
            savedTeacher = entityManager.merge(teacher);
        }
        entityManager.flush();
        return savedTeacher;
    }

    @Override
    public void delete(Teacher teacher) {
        entityManager.remove(teacher);
    }
}
