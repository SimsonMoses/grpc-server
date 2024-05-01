package com.learn.service.grpc;

import com.learn.grpc.user.User;
import com.learn.grpc.user.UserPresent;
import com.learn.grpc.user.UserRequest;
import com.learn.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserServiceGrpc extends com.learn.grpc.user.UserServiceGrpc.UserServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(UserServiceGrpc.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUser(UserRequest request, StreamObserver<User> responseObserver) {
        log.info("getUser");
        com.learn.model.User user = userRepository.findByUserEmail(request.getEmail());
        if (user != null) {
            User userResponse = User.newBuilder()
                    .setUserDepartment(user.getUserDepartment())
                    .setUserGender(user.getUserGender())
                    .setUserFirstName(user.getUserFirstName())
                    .setUserEmail(user.getUserEmail())
                    .setUserLastName(user.getUserLastName())
                    .setUserPhoneNumber(user.getUserPhoneNumber())
                    .build();
            responseObserver.onNext(userResponse);
            responseObserver.onCompleted();
        }
        User userResponse = null;
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void userExist(UserRequest request, StreamObserver<UserPresent> responseObserver) {
        log.info("UserRequest {}",request);
        com.learn.model.User user = userRepository.findByUserEmail(request.getEmail());
        boolean isUserPresent = false;
        if (user != null) {
            isUserPresent = true;
        }
        UserPresent userPresent = UserPresent.newBuilder().setIsUserPresent(isUserPresent).build();
        responseObserver.onNext(userPresent);
        responseObserver.onCompleted();
    }
}
