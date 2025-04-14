package com.app.guimscore.view.model;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;

import java.util.UUID;

public record DataReqDto(
        UUID uuid,
        String nameData,
        Integer value,
        Integer maxValue,
        Integer minValue,
        UserModel userModel,
        GameServerModel gameServer
) {
}
