package com.app.guimscore.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "data-game")
public class DataModel {
    @Id
    private UUID uuid;
    @NotNull(message = "É necessario preencher o nome do novo dado")
    private String nameData;
    @NotNull(message = "É necessario preencher o valor desse dado")
    private Integer value;
    private Integer maxValue;
    private Integer minValue;
    @NotNull(message = "É necessario saber a referencia do jogador")
    private UserModel player;
    @NotNull(message = "É necessario saber a referencia do servidor")
    private GameServerModel gameServerModel;

    public DataModel() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNameData() {
        return nameData;
    }

    public void setNameData(String nameData) {
        this.nameData = nameData;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public UserModel getPlayer() {
        return player;
    }

    public void setPlayer(UserModel player) {
        this.player = player;
    }

    public GameServerModel getGameServerModel() {
        return gameServerModel;
    }

    public void setGameServerModel(GameServerModel gameServerModel) {
        this.gameServerModel = gameServerModel;
    }
}
