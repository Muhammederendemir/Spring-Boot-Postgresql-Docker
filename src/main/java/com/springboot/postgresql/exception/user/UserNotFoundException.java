package com.springboot.postgresql.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error: User is not found.")
public class UserNotFoundException extends RuntimeException {

}
