package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.user.UserRequest;
import com.radouaneoubakhane.userservice.dto.user.UserResponse;

public interface UserService {
    UserResponse getMyUser();

    UserResponse updateMyUser(UserRequest userRequest);

    UserResponse createMyUser(UserRequest userRequest);

    void deleteMyUser();
}
