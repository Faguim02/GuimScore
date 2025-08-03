package com.app.guimscore.view.model;

public record DataReqDto(
        String nameData,
        Integer value,
        Integer maxValue,
        Integer minValue
) {
}
