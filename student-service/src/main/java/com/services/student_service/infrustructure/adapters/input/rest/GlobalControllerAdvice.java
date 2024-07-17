package com.services.student_service.infrustructure.adapters.input.rest;

import com.services.student_service.domain.exception.StudentNotFoundException;
import com.services.student_service.domain.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.services.student_service.utils.ErrorCatalog.STUDENT_NOT_FOUND;
import static com.services.student_service.utils.ErrorCatalog.INVALID_STUDENT;
import static com.services.student_service.utils.ErrorCatalog.GENERIC_ERROR;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException(){
        return ErrorResponse.builder()
                .code(STUDENT_NOT_FOUND.getCode())
                .code(STUDENT_NOT_FOUND.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();

        return ErrorResponse.builder()
                .code(INVALID_STUDENT.getCode())
                .code(INVALID_STUDENT.getMessage())
                .details(result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e ){
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .code(GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
