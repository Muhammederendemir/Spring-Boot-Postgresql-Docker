package com.springboot.postgresql.service;

import com.springboot.postgresql.exception.user.EmailAlreadyExistsException;
import com.springboot.postgresql.exception.user.UserAlreadyExistsException;
import com.springboot.postgresql.exception.user.UserNotFoundException;
import com.springboot.postgresql.model.User;
import com.springboot.postgresql.payload.request.UserCreateRequest;
import com.springboot.postgresql.payload.request.UserUpdateRequest;
import com.springboot.postgresql.payload.response.MessageResponse;
import com.springboot.postgresql.repository.UserRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
public class UserServiceImpl implements UserService{

    @NonNull UserRepository userRepository;

    @NonNull ModelMapper modelMapper;

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {

        Optional<User> optionalUser=userRepository.findById(userCreateRequest.getId());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user=modelMapper.map(userCreateRequest,User.class);

        return userRepository.save(user);

    }

    @Override
    public User updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());

        if(user.getEmail()!=userUpdateRequest.getEmail() && userRepository.existsByEmail(userUpdateRequest.getEmail())){
            throw new EmailAlreadyExistsException();
        }
        user.setEmail(userUpdateRequest.getEmail());

        return userRepository.save(user);

    }

    @Override
    public MessageResponse deleteUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);

        return new MessageResponse(userId+" User deleted");
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException());
    }

    @Override
    public Page<User> listUser(Pageable page) {
        return userRepository.findAll(page);
    }


}
