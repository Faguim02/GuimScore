package com.app.guimscore.repository;

import com.app.guimscore.model.ItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ItemRepository extends MongoRepository<ItemModel, UUID> {
}
