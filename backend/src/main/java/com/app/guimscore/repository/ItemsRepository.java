package com.app.guimscore.repository;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.ItemsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemsRepository extends MongoRepository<ItemsModel, UUID> {

    List<ItemsModel> findByGameServerModel(GameServerModel gameServerModel);
}
