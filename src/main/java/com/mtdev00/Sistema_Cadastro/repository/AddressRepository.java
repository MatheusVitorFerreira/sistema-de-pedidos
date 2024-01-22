package com.mtdev00.Sistema_Cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtdev00.Sistema_Cadastro.Domain.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
