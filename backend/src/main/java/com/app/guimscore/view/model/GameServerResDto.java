package com.app.guimscore.view.model;

import java.util.Date;
import java.util.UUID;

public record GameServerResDto(UUID id, String nameServer, String description, Date dateAt) {
}
