package com.app.guimscore.repository;

import com.app.guimscore.model.GameServerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameServerRepository extends MongoRepository<GameServerModel, UUID> {
}
