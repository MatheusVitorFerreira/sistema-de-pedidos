package com.mtdev00.Sistema_Cadastro.Resourche;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtdev00.Sistema_Cadastro.DTO.AuthenticationDTO;
import com.mtdev00.Sistema_Cadastro.DTO.RegisterDTO;
import com.mtdev00.Sistema_Cadastro.Service.AuthorizationService;
import com.mtdev00.Sistema_Cadastro.Service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationResource { 
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	TokenService tokenService;
	
  
    
    @PostMapping("/login")
    public ResponseEntity <Object>login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
    	return authorizationService.login(authenticationDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return authorizationService.register(registerDTO);
    }
}