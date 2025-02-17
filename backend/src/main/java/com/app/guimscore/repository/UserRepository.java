package com.app.guimscore.repository;

import com.app.guimscore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<UserModel, UUID> {
}
