package com.app.guimscore.dto;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.roles.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserDto {
    private UUID uuid;
    @NotNull(message = "É necessario preencher o nome")
    private String name;
    @Email
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

    public @NotNull(message = "É necessario preencher o nome") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "É necessario preencher o nome") String name) {
        this.name = name;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotNull(message = "É necessario preencher a senha") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "É necessario preencher a senha") String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public @NotNull(message = "É necessario preencher o tipo de permição") UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NotNull(message = "É necessario preencher o tipo de permição") UserRole userRole) {
        this.userRole = userRole;
    }

    public GameServerModel getGameServerModel() {
        return gameServerModel;
    }

    public void setGameServerModel(GameServerModel gameServerModel) {
        this.gameServerModel = gameServerModel;
    }
}
