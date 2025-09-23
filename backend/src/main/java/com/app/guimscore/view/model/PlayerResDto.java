package com.app.guimscore.view.model;

import java.util.Date;
import java.util.UUID;

public record PlayerResDto(
        UUID id,
        String name,
        Date dateOfBirth,
        UUID gameServerId
) {
}
