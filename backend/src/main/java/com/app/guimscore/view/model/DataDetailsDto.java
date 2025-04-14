package com.app.guimscore.view.model;

import java.util.UUID;

public record DataDetailsDto(
        UUID uuid,
        String nameData,
        Integer value,
        Integer maxValue,
        Integer minValue
) {
}
