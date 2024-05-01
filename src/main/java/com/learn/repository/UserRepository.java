package com.learn.repository;


import com.learn.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

//    User findByUserFirstNameAndUserLastNameAndDateOfBirth(String userFirstName, String userLastName, Date dateOfBirth);

//    User findByUserPhoneNumberOrUserEmail(int userPhoneNumber, String userEmail);

    boolean existsByUserPhoneNumberOrUserEmail(int userPhoneNumber, String userEmail);
    User findByUserEmail(String userEmail);
    User findByUserPhoneNumber(int userPhoneNumber);
}
