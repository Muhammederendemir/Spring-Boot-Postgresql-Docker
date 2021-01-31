package com.springboot.postgresql.controller;

import com.springboot.postgresql.model.User;
import com.springboot.postgresql.payload.request.UserCreateRequest;
import com.springboot.postgresql.payload.request.UserUpdateRequest;
import com.springboot.postgresql.service.UserService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@RequestMapping("/versions/1/users")
public class UserController {

    @NonNull UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> listUser(Pageable page) {
        return userService.listUser(page);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@NotBlank @PathVariable Long userId) {
        userService.deleteUser(userId);
    }


    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@NotBlank @PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@NotBlank @PathVariable Long userId,@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId,userUpdateRequest);
    }

}
