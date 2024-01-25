package com.mtdev00.Sistema_Cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtdev00.Sistema_Cadastro.Domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	 List<Product> findByNameIgnoreCaseContaining(String name);


}
