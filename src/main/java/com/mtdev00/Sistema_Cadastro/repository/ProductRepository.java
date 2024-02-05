package com.mtdev00.Sistema_Cadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mtdev00.Sistema_Cadastro.Domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameIgnoreCaseContaining(String name);

	@Query("SELECT obj FROM Product obj WHERE name LIKE concat('%',:name,'%')")
	Page<Product> search(@Param("name") String name, Pageable pageable);

	Optional<Product> findByNameIgnoreCase(String name);
	
	@Modifying
    @Query("UPDATE Product p SET p.stockQuantity = p.stockQuantity - :quantity WHERE p.id = :id")
    int updateStockQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);
}
