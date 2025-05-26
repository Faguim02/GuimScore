package com.app.guimscore.repository;

import com.app.guimscore.model.ItemModel;
import com.app.guimscore.model.ItemsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, UUID> {
    List<ItemModel> findByListItem(ItemsModel listItem);
}
