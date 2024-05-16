package edu.mtdev00.sistemapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import edu.mtdev00.sistemapedido.domain.User;

public interface UserRepository extends JpaRepository<User, Long > {
    UserDetails findByLogin(String login);
}
	