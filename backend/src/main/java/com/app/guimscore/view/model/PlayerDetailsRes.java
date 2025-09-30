package com.app.guimscore.view.model;

import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.ItemsModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record PlayerDetailsRes(UUID id, String name, Date dateOfBirth, UUID gameServerId, List<ItemsModel> items, List<DataModel> data) {
}
