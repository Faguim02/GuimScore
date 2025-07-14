package com.app.guimscore.view.model;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;

import java.util.UUID;

public record DataReqDto(
        String nameData,
        Integer value,
        Integer maxValue,
        Integer minValue
) {
}
