package edu.mtdev00.sistemapedido.resource;

import edu.mtdev00.sistemapedido.dto.OrderDTO;
import edu.mtdev00.sistemapedido.domain.Order;
import edu.mtdev00.sistemapedido.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

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
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> orders = service.findAll();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody Order obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}