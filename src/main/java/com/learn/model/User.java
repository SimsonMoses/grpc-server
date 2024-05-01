package com.learn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import java.util.List;

@Document
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String userId;
    @Column(name = "userFirstName", length = 30)
    private String userFirstName;
    private String userLastName;
    @Column(name = "userEmail", length = 40)
    private String userEmail;
    private String userGender;
    private Date dateOfBirth;
    @Column(length = 10)
    private int userPhoneNumber;
    private String userDepartment;
    private String userJoiningDate;
    private List<String> userAddressId;
    @Override
    public String toString(){
        return "User to String";
    }
}