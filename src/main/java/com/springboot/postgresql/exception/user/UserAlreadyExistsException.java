package com.springboot.postgresql.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Error: User is already taken!")
public class UserAlreadyExistsException extends RuntimeException {

}
