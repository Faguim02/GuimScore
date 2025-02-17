package com.app.guimscore.model;

import com.app.guimscore.model.roles.UserRole;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user")
public class UserModel {
    @Id
    private UUID uuid;
    @NotNull(message = "É necessario preencher o nome")
    private String name;
    private String email;
    @NotNull(message = "É necessario preencher a senha")
    private String password;
    private String avatar;
    @NotNull(message = "É necessario preencher o tipo de permição")
    private UserRole userRole;
    private GameServerModel gameServerModel;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public GameServerModel getGameServerModel() {
        return gameServerModel;
    }

    public void setGameServerModel(GameServerModel gameServerModel) {
        this.gameServerModel = gameServerModel;
    }
}
