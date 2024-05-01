package com.learn.service;

import com.learn.exception.CustomException;
import com.learn.model.User;
import com.learn.request.CreateUser;
import com.learn.response.CommonResponse;

public interface UserService {
    CommonResponse createUser(CreateUser user);
    CommonResponse getAllUsers();
    CommonResponse getUserByUserEmail(String userEmail);
    CommonResponse getUserByPhoneNumber(int userPhoneNumber);
    CommonResponse updateUser(User userUpdated) throws CustomException;

    CommonResponse deleteUser(String userId) throws CustomException;
}
