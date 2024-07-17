package com.services.student_service.application.service;

import com.services.student_service.application.ports.input.StudentServicePort;
import com.services.student_service.application.ports.output.StudentPersistencePort;
import com.services.student_service.domain.exception.StudentNotFoundException;
import com.services.student_service.domain.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService implements StudentServicePort {

    private final StudentPersistencePort studentServicePort;


    @Override
    public Student findById(Long id) {
        return studentServicePort.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public List<Student> findAll() {
        return studentServicePort.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentServicePort.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentServicePort.findById(id)
                .map(savedStudent-> {
                    savedStudent.setFirstname(student.getFirstname());
                    savedStudent.setLastname(student.getLastname());
                    savedStudent.setAge(student.getAge());
                    savedStudent.setId(id);
                    return studentServicePort.save(savedStudent);
                })
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(studentServicePort.findById(id).isEmpty()){
            throw new StudentNotFoundException();
        }else{
            log.info("Deleting.... ");
            studentServicePort.deleteById(id);
        }
    }
}
