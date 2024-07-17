package com.services.student_service.infrustructure.adapters.input.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    private String address;
}
