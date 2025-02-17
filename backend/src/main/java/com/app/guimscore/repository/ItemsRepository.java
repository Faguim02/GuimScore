package com.app.guimscore.repository;

import com.app.guimscore.model.ItemsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ItemsRepository extends MongoRepository<ItemsModel, UUID> {
}
