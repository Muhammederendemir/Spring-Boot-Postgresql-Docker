package com.springboot.postgresql.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotNull(message = "Please enter id")
    Long id;

    @NotBlank
    @Size(max = 50)
    String firstName;

    @NotBlank
    @Size(max = 50)
    String lastName;

    @Size(max = 50)
    @Email
    String email;

}
