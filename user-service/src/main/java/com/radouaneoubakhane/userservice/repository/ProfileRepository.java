package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
