package com.app.guimscore.view.model;

import java.util.UUID;

public record ValueToDataReqDto(
        Integer value,
        UUID dataId,
        UUID playerId,
        UUID gameId
) {
}
