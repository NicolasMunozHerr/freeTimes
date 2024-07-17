package com.services.student_service.infrustructure.adapters.input.rest.mapper;

import com.services.student_service.domain.model.Student;
import com.services.student_service.infrustructure.adapters.input.rest.model.request.StudentRequest;
import com.services.student_service.infrustructure.adapters.input.rest.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {

    Student toStudent(StudentRequest studentRequest);

    StudentResponse toStudentResponse(Student student);

    List<StudentResponse> toStudentResponseList(List<Student> studentList);
}
