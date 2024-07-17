package com.services.student_service;

import com.services.student_service.domain.model.Student;
import com.services.student_service.infrustructure.adapters.output.persistence.entity.StudentEntity;
import com.services.student_service.infrustructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentMappePersistenceMapperTest {

    private StudentPersistenceMapper mapper = Mappers.getMapper(StudentPersistenceMapper.class);


    @Test
    public void testMapping() {
        Student student = Student.builder()
                .id(1L)
                .firstname("John")
                .lastname("Doe")
                .age(25)
                .address("123 Main St")
                .build();

        StudentEntity studentEntity = mapper.toStudentEntity(student);
        assertEquals(student.getAge(), studentEntity.getAge());

        Student mappedStudent = mapper.toStudent(studentEntity);
        assertEquals(studentEntity.getAge(), mappedStudent.getAge());
    }
}
