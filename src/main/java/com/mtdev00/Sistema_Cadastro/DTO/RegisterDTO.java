package com.mtdev00.Sistema_Cadastro.DTO;

import javax.validation.constraints.NotNull;

import com.mtdev00.Sistema_Cadastro.Domain.UserRole;

public record RegisterDTO (@NotNull String login,@NotNull String password,@NotNull UserRole role) {

}
