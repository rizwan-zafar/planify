package com.planify.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAdminRequestDto(

        @NotBlank(message = ERROR_MESSAGE_NAME)
        String name,

        @NotBlank(message = ERROR_MESSAGE_EMAIL)
        String email,
        @NotBlank(message = ERROR_MESSAGE_PASSWORD)
        String password,

        @NotBlank(message = ERROR_MESSAGE_CONFIRM_PASSWORD)
        String confirm_password,

        @NotBlank(message = ERROR_MESSAGE_ADMIN_ROLE)
        String role

) {
    private  static final String ERROR_MESSAGE_NAME = "Name can not be empty";
    private  static final String ERROR_MESSAGE_EMAIL = "Email can not be empty";
    private  static final String ERROR_MESSAGE_PASSWORD = "Password can not be empty";
    private  static final String ERROR_MESSAGE_CONFIRM_PASSWORD = "Confirm password can not be empty";
    private  static final String ERROR_MESSAGE_ADMIN_ROLE = "SELECT ROLE";

}
