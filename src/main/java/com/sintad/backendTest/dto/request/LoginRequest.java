package com.sintad.backendTest.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class LoginRequest {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
