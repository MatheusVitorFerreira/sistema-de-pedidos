package edu.mtdev00.sistemapedido.resource;


import edu.mtdev00.sistemapedido.dto.AuthenticationDTO;
import edu.mtdev00.sistemapedido.dto.RegisterDTO;
import edu.mtdev00.sistemapedido.service.AuthorizationService;
import edu.mtdev00.sistemapedido.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationResource { 
	@Autowired
    private AuthorizationService authorizationService;
    
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        return authorizationService.login(authenticationDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return authorizationService.register(registerDTO);
    }
    
    @GetMapping("/login") 
    public String showLoginForm() {
        return "login"; 
    }
}