package edu.mtdev00.sistemapedido.service;



import java.util.Date;

import javax.validation.Valid;

import edu.mtdev00.sistemapedido.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.mtdev00.sistemapedido.dto.AuthenticationDTO;
import edu.mtdev00.sistemapedido.dto.LoginResponseDTO;
import edu.mtdev00.sistemapedido.dto.RegisterDTO;
import edu.mtdev00.sistemapedido.domain.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



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
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token.getToken(), token.getExpiration());
        
        return ResponseEntity.ok(loginResponseDTO);
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
	