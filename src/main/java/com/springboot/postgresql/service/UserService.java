package com.springboot.postgresql.service;

import com.springboot.postgresql.model.User;
import com.springboot.postgresql.payload.request.UserCreateRequest;
import com.springboot.postgresql.payload.request.UserUpdateRequest;
import com.springboot.postgresql.payload.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    User createUser(UserCreateRequest userCreateRequest);
    User updateUser(Long userId,UserUpdateRequest userUpdateRequest);
    MessageResponse deleteUser(Long userId);
    User getUser(Long userId);
    Page<User> listUser(Pageable page);
}
