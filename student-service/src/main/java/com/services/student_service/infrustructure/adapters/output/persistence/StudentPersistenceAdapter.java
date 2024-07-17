package com.services.student_service.infrustructure.adapters.output.persistence;

import com.services.student_service.application.ports.output.StudentPersistencePort;
import com.services.student_service.domain.model.Student;
import com.services.student_service.infrustructure.adapters.output.persistence.entity.StudentEntity;
import com.services.student_service.infrustructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import com.services.student_service.infrustructure.adapters.output.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPersistencePort {

    private final StudentRepository studentRepository;
    private final StudentPersistenceMapper mapper;

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id)
                .map(mapper::toStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(mapper::toStudent)
                .collect(Collectors.toList());
    }

    @Override
    public Student save(Student student) {
        StudentEntity studentEntity  = mapper.toStudentEntity(student);
        log.info("AGEEEE ANTES DE GUARDAR {}", studentEntity.getAge());
        log.info(String.valueOf(mapper.toStudentEntity(student).getAge()));
        return mapper.toStudent(studentRepository.save(studentEntity));
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
