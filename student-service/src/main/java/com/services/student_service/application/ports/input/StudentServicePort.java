package com.services.student_service.application.ports.input;

import com.services.student_service.domain.model.Student;

import java.util.List;

public interface StudentServicePort {

    Student findById(Long id);

    List<Student> findAll();

    Student save(Student student);

    Student update(Long id, Student student);

    void deleteById(Long id);

}
