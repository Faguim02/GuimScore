package com.app.guimscore.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class ItemsModel {
    @Id
    private UUID uuid;
    private String listName;
    private GameServerModel gameServerModel;
    private UserModel player;

    public ItemsModel() {
        this.uuid = UUID.randomUUID();
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
