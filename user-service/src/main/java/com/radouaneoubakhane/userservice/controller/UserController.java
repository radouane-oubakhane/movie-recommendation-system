package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.user.UserRequest;
import com.radouaneoubakhane.userservice.dto.user.UserResponse;

public interface UserController {

    UserResponse getMyUser();


    UserResponse updateMyUser(UserRequest userRequest);


    UserResponse createMyUser(UserRequest userRequest);

    void deleteMyUser();
}
