package com.app.guimscore.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "game-server")
public class GameServerModel {
    @Id
    private UUID uuid;
    @NotNull(message = "É necessario preencher o nome do serviço")
    private String nameServer;
    private String description;
    private Date dateUpdate;
    private Date dateAt;
    private UserModel user;

    public GameServerModel(String nameServer, String description) {
        this.uuid = UUID.randomUUID();
        this.nameServer = nameServer;
        this.description = description;
        this.dateUpdate = new Date();
        this.dateAt = new Date();
    }

    public GameServerModel() {
        this.uuid = UUID.randomUUID();
        this.dateAt = new Date();
        this.dateUpdate = new Date();
    }

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
