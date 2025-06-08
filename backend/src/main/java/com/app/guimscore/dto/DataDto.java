package com.app.guimscore.dto;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class DataDto {

    private UUID uuid;
    private String nameData;
    private Integer value;
    private Integer maxValue;
    private Integer minValue;
    private UserModel player;
    private GameServerModel gameServerModel;

    public DataDto(String nameData, Integer value, Integer maxValue, Integer minValue) {
        this.uuid = UUID.randomUUID();
        this.nameData = nameData;
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public DataDto() {
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
