package com.enigma.language_learning_center.repository.impl;

import com.enigma.language_learning_center.model.entity.Student;
import com.enigma.language_learning_center.repository.StudentRepository;
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
public class StudentRepositoryImpl implements StudentRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_student", Student.class);
        return query.getResultList();
    }

    @Override
    public Optional<Student> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_student WHERE id = :id", Student.class);
        query.setParameter("id", id);
        List<Student> students = query.getResultList();
        return students.isEmpty() ? Optional.empty() : Optional.of(students.get(0));
    }

    @Override
    @Transactional
    public Student saveAndFlush(Student student) {
        Student savedStudent;
        if (student.getId() == null) {
            entityManager.persist(student);
            savedStudent = student;
        }else{
            savedStudent = entityManager.merge(student);
        }
        entityManager.flush();
        return savedStudent;
    }

    @Override
    @Transactional
    public void delete(Student student) {
        entityManager.remove(student);
    }
}
