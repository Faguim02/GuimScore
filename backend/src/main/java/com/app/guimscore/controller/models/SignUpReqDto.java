package com.app.guimscore.controller.models;

import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.roles.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SignUpReqDto(
        @NotNull(message = "É necessario preencher o nome")
        String name,
        @Email
        String email,
        @NotNull(message = "É necessario preencher a senha")
        String password
) {
}
