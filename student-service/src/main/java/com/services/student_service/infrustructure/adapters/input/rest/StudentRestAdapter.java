package com.services.student_service.infrustructure.adapters.input.rest;

import com.services.student_service.application.ports.input.StudentServicePort;
import com.services.student_service.infrustructure.adapters.input.rest.mapper.StudentRestMapper;
import com.services.student_service.infrustructure.adapters.input.rest.model.request.StudentRequest;
import com.services.student_service.infrustructure.adapters.input.rest.model.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentRestAdapter {

    private final StudentServicePort studentServicePort;
    private final StudentRestMapper restMapper;

    @GetMapping("/v1/api")
    public List<StudentResponse> findAll(){
        return restMapper.toStudentResponseList(studentServicePort.findAll());
    }

    @GetMapping("/v1/api/{id}")
    public StudentResponse findById(@PathVariable Long id){

        return restMapper.toStudentResponse(studentServicePort.findById(id));
    }

    @PostMapping("/v1/api")
    public  ResponseEntity<StudentResponse> save(@Valid @RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(restMapper.toStudentResponse(studentServicePort.save(restMapper.toStudent(studentRequest))), HttpStatus.CREATED);
    }

    @PutMapping("/v1/api/{id}")
    public StudentResponse update(@Valid @RequestBody StudentRequest studentRequest, @PathVariable Long id){
        return restMapper.toStudentResponse(studentServicePort.update(id, restMapper.toStudent(studentRequest)));
    }

    @DeleteMapping("/v1/api/{id}")
    public void delete(@PathVariable Long id){
        studentServicePort.deleteById(id);

    }


}
