package com.mtdev00.Sistema_Cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.mtdev00.Sistema_Cadastro.Domain.User;

public interface UserRepository extends JpaRepository<User, Long > {
    UserDetails findByLogin(String login);
}
	