package com.app.guimscore.repository;

import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DataRepository extends MongoRepository<DataModel, UUID> {

    List<DataModel> findByGameServerModel(GameServerModel gameServerModel);

}
