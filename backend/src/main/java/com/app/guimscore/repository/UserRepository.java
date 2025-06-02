package com.app.guimscore.repository;

import com.app.guimscore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<UserModel, UUID> {
    boolean existsByName(String name);
    UserDetails findByName(String name);
}
