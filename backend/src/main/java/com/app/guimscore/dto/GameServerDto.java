package com.app.guimscore.dto;

import com.app.guimscore.model.UserModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class GameServerDto {
    private UUID uuid;
    private String nameServer;
    private String description;
    private Date dateUpdate;
    private Date dateAt;
    private UserModel user;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
