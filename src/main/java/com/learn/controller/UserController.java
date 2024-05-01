package com.learn.controller;

import com.learn.enumeration.ResponseStatus;
import com.learn.model.User;
import com.learn.request.CreateUser;
import com.learn.response.CommonResponse;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("createUser")
    public CommonResponse createUser(@Validated @RequestBody CreateUser createUser) {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.createUser(createUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @GetMapping("getAllUsers")
    public CommonResponse getAllUsers() {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.getAllUsers();
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @GetMapping("getUserByEmail")
    public CommonResponse getUserByUserEmail(@RequestParam String userEmail) {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.getUserByUserEmail(userEmail);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @GetMapping("getUserByPhoneNumber")
    public CommonResponse getUserByPhoneNumber(@RequestParam int userPhoneNumber) {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.getUserByPhoneNumber(userPhoneNumber);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @PutMapping("updateUser")
    public CommonResponse updateUser(@RequestBody User user) {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.updateUser(user);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @DeleteMapping("deleteUser")
    public CommonResponse deleteUser(@RequestParam String userId) {
        CommonResponse response = new CommonResponse();
        try {
            response = userService.deleteUser(userId);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

}
