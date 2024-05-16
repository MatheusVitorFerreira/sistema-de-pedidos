package edu.mtdev00.sistemapedido.dto;

public class LoginResponseDTO {

    private final String token;
    private final String expiration;

    public LoginResponseDTO(String token, String expiration) {
        this.token = token;
        this.expiration = expiration;
    }

	public String getToken() {
        return token;
    }

    public String getExpiration() {
        return expiration;
    }
}
