package com.app.guimscore.view.model;

import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.UserModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record GameServerDetailsDto(
        UUID id,
        String nameServer,
        String description,
        Date dateAt,
        List<UserModel> users,
        List<DataModel> dataList
) {
}
