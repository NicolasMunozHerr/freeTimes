package com.services.student_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentString {
    private String id;

    private String firstname;

    private String lastname;

    private String age;

    private String address;
}
