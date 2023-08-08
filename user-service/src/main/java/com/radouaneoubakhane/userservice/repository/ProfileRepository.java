package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.domain.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Optional<Profile> findByUserId(long l);

    List<Profile> findByFirstNameContainingOrLastNameContaining(String name, String name1);
}
