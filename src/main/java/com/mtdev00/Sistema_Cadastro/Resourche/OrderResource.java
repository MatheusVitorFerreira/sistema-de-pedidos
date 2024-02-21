package com.mtdev00.Sistema_Cadastro.Resourche;

import java.io.Serializable;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mtdev00.Sistema_Cadastro.DTO.OrderDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Order;
import com.mtdev00.Sistema_Cadastro.Service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderResource implements Serializable{
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("paymentOrderService")
	private OrderService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> find(@PathVariable Long id) {
	  OrderDTO dto = service.find(id);
	  return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody Order obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}