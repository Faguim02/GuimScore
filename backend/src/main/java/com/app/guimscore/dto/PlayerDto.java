package com.app.guimscore.dto;

import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.ItemsModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PlayerDto {
    private UUID id;
    private String name;
    private String password;
    private Date dateOfBirth;

    private UUID gameServerId;

    private List<ItemsModel> items;
    private List<DataModel> data;

    public PlayerDto() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getGameServerId() {
        return gameServerId;
    }

    public void setGameServerId(UUID gameServerId) {
        this.gameServerId = gameServerId;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
