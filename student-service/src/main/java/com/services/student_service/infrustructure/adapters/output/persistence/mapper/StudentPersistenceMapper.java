package com.services.student_service.infrustructure.adapters.output.persistence.mapper;

import com.services.student_service.domain.model.Student;
import com.services.student_service.infrustructure.adapters.output.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentPersistenceMapper {

    StudentEntity toStudentEntity(Student student);

    //@Mapping(target = "age" , source = "age")
    //StudentEntity toStudent(Student student);
    Student toStudent(StudentEntity studentEntity);

    List<Student> toStudentList(List<StudentEntity> studentEntities);

}
