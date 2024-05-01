package com.learn.service.impl;

import com.learn.enumeration.ResponseStatus;
import com.learn.exception.CustomException;
import com.learn.model.User;
import com.learn.repository.UserRepository;
import com.learn.request.CreateUser;
import com.learn.response.CommonResponse;
import com.learn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommonResponse createUser(CreateUser createUser) {
        CommonResponse response = new CommonResponse();
        boolean doesUserExist = userRepository.existsByUserPhoneNumberOrUserEmail(createUser.getUserPhoneNumber(), createUser.getUserEmail());
        if (!doesUserExist) {
            User user = modelMapper.map(createUser, User.class);
            userRepository.save(user);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("User has created successfully");
            response.setData(createUser);
        } else {
            response.setCode(409);
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMessage("User with phone or email already exists!");
            response.setData(createUser);
        }
        return response;
    }

    @Override
    public CommonResponse getAllUsers() {
        CommonResponse response = new CommonResponse();
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("Users have retrived successfully");
            response.setData(users);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMessage("No Users Exists!");
        }
        return response;
    }

    @Override
    public CommonResponse getUserByUserEmail(String userEmail) {
        CommonResponse response = new CommonResponse();
        User user = userRepository.findByUserEmail(userEmail);
        if (user != null) {
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("User With the email fetched Successfully");
            response.setData(user);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setSuccessMessage("User With the email doesn't exist!");
            response.setData(userEmail);
        }
        return response;
    }

    @Override
    public CommonResponse getUserByPhoneNumber(int userPhoneNumber) {
        CommonResponse response = new CommonResponse();
        User user = userRepository.findByUserPhoneNumber(userPhoneNumber);
        if (user != null) {
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("User With the Phone Number fetched Successfully");
            response.setData(user);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setSuccessMessage("User With the email doesn't exist!");
            response.setData(userPhoneNumber);
        }
        return response;
    }

    @Override
    public CommonResponse updateUser(User userUpdated) throws CustomException {
        CommonResponse response = new CommonResponse();
        User user = userRepository.findById(userUpdated.getUserId()).orElseThrow(() -> new CustomException("Data not found", new User()));
        if (user != null) {
            userRepository.save(userUpdated);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("User Updated Successfully!");
            response.setData(user);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setSuccessMessage("User doesn't exist!");
            response.setData(userUpdated);
        }
        return response;
    }

    @Override
    public CommonResponse deleteUser(String userId) throws CustomException {
        CommonResponse response = new CommonResponse();
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("Data not found", new User()));
        if (user != null) {
            userRepository.deleteById(userId);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("User deleted Successfully!");
            response.setData(user);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setSuccessMessage("User doesn't exist!");
        }
        return response;
    }
}
