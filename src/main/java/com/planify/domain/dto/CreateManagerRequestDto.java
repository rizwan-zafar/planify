package com.planify.domain.dto;

import com.planify.domain.enums.ManagerGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateManagerRequestDto(

        @NotBlank(message = ERROR_MESSAGE_NAME_LENGTH)
        @Length(min = 2, max = 30)
        String name,

        @NotBlank(message = ERROR_MESSAGE_EMAIL_REQ)
        @Email
        String email,

        @NotBlank(message = ERROR_MESSAGE_DEPARTMENT_REQ)
        String department,

        @NotBlank(message = ERROR_MESSAGE_GENDER_REQ)
        @Enumerated(EnumType.STRING)
        ManagerGender gender

) {
    private  static final String ERROR_MESSAGE_NAME_LENGTH = "Name can not be less then 2 characters";
    private static final String ERROR_MESSAGE_EMAIL_REQ="Email can not be null";
    private static final String ERROR_MESSAGE_DEPARTMENT_REQ="Department can not be null";
    private static final String ERROR_MESSAGE_GENDER_REQ="Select the correct Gender MALE/FEMALE";
}

// request
// name
// email
// department
// gender