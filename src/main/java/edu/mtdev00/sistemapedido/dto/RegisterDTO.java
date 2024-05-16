package edu.mtdev00.sistemapedido.dto;

import javax.validation.constraints.NotNull;

import edu.mtdev00.sistemapedido.domain.UserRole;

public record RegisterDTO (@NotNull String login,@NotNull String password,@NotNull UserRole role) {

}
