package com.app.guimscore.dto;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;

import java.util.UUID;

public class ListItemDto {
    private UUID uuid;
    private String listName;
    private GameServerModel gameServerModel;
    private UserModel player;
}
