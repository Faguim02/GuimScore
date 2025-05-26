package com.app.guimscore.repository;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameServerRepository extends MongoRepository<GameServerModel, UUID> {

    public List<GameServerModel> findByUser(UserModel userModel);

}
