package com.app.guimscore.view.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignUpReqDto(
        @NotNull(message = "É necessario preencher o nome")
        String name,
        @Email
        String email,
        @NotNull(message = "É necessario preencher a senha")
        String password
) {
}
