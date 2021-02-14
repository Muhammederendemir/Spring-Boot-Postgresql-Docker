package com.springboot.postgresql.controller;

import com.springboot.postgresql.model.User;
import com.springboot.postgresql.payload.request.UserCreateRequest;
import com.springboot.postgresql.payload.request.UserUpdateRequest;
import com.springboot.postgresql.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/versions/1/users")
public class UserController {

    @NonNull UserService userService;

    @Operation(summary = "Create new a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "New user created",
                    content = {@Content(mediaType = "application/json")}
            ),
            @ApiResponse(responseCode = "409",
                    description = "User with id already exists",
                    content = @Content)
    }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All users listed",
                    content = {@Content(mediaType = "application/json")}
            )
    }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> listUser(Pageable page) {
        return userService.listUser(page);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A user has been deleted",
                    content = {@Content(mediaType = "application/json")}
            ),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    }
    )
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@NotBlank @PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @Operation(summary = "Get a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A user has been brought in",
                    content = {@Content(mediaType = "application/json")}
            ),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    }
    )

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@NotBlank @PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }


    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A user has been updated",
                    content = {@Content(mediaType = "application/json")}
            )
    }
    )
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@NotBlank @PathVariable Long userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);
    }

}
