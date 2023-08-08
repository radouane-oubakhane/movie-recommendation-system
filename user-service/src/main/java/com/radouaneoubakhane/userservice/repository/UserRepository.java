package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>


{
    boolean existsByUsername(String username);
}
