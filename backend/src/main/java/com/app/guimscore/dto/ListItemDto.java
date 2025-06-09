package com.app.guimscore.dto;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;

import java.util.UUID;

public class ListItemDto {
    private UUID uuid;
    private String listName;
    private GameServerModel gameServerModel;
    private UserModel player;

    public ListItemDto() {
    }

    public ListItemDto(String listName) {
        this.listName = listName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public GameServerModel getGameServerModel() {
        return gameServerModel;
    }

    public void setGameServerModel(GameServerModel gameServerModel) {
        this.gameServerModel = gameServerModel;
    }

    public UserModel getPlayer() {
        return player;
    }

    public void setPlayer(UserModel player) {
        this.player = player;
    }
}
