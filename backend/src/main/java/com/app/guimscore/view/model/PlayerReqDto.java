package com.app.guimscore.view.model;

import java.util.UUID;

public record PlayerReqDto(
        String name,
        String password,
        String dateOfBirth,
        UUID gameServerId
) {
}
