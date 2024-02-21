package com.mtdev00.Sistema_Cadastro.Service;



import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mtdev00.Sistema_Cadastro.DTO.AuthenticationDTO;
import com.mtdev00.Sistema_Cadastro.DTO.LoginResponseDTO;
import com.mtdev00.Sistema_Cadastro.DTO.RegisterDTO;
import com.mtdev00.Sistema_Cadastro.Domain.User;
import com.mtdev00.Sistema_Cadastro.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private org.springframework.context.ApplicationContext context;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
    	if(this.userRepository.findByLogin(registerDTO.login()) !=null) return ResponseEntity.badRequest().build();
    	String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
    	User newUser = new User(registerDTO.login(),encryptedPassword, registerDTO.role());
    	newUser.setCreatedAt(new Date(System.currentTimeMillis()));
    	this.userRepository.save(newUser);
    	return ResponseEntity.ok().build();
    }
}
	