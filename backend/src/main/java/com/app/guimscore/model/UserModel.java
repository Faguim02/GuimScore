package com.app.guimscore.model;

import com.app.guimscore.model.roles.UserRole;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Document(collection = "user")
public class UserModel implements UserDetails {
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

    public UserModel(String name, String email, String password, String avatar) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public UserModel() {
        this.uuid = UUID.randomUUID();
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PLAYER"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
