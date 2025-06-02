package com.app.guimscore.repository;

import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping
public interface DataRepository extends MongoRepository<DataModel, UUID> {

    List<DataModel> findByGameServerModel(GameServerModel gameServerModel);

}
