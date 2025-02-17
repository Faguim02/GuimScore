package com.app.guimscore.repository;

import com.app.guimscore.model.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DataRepository extends MongoRepository<DataModel, UUID> {
}
