package edu.mtdev00.sistemapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mtdev00.sistemapedido.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	@Query("SELECT a FROM Address a WHERE a.client.id = :clients_id")
	Address findByClientId(@Param("clients_id") Long clientId);
}
