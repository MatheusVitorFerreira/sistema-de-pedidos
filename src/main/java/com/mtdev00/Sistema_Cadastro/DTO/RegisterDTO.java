package com.mtdev00.Sistema_Cadastro.DTO;

import com.mtdev00.Sistema_Cadastro.config.UserRole;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO (@NotNull String login,@NotNull String password,@NotNull UserRole role) {

}
