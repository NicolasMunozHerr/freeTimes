package com.services.student_service.infrustructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotBlank(message = "Field firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "Field lastname cannot be empty")
    private String lastname;
    @NotNull(message = "Field age cannot be empty")
    private Integer age;
    @NotBlank(message = "Field address cannot be empty")
    private String address;
}
