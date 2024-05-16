package edu.mtdev00.sistemapedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mtdev00.sistemapedido.domain.OrderItems;

@Repository
public interface ItemOrderRepository extends JpaRepository<OrderItems, Long> {

}
