package com.app.guimscore.model.roles;

public enum UserRole {
    ADMIN("admin"),
    PLAYER("player");

    private String userRole;
    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
