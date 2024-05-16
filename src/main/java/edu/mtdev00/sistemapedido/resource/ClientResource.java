package edu.mtdev00.sistemapedido.resource;

import edu.mtdev00.sistemapedido.dto.ClientDTO;
import edu.mtdev00.sistemapedido.dto.ClientDTOComplet;
import edu.mtdev00.sistemapedido.domain.Address;
import edu.mtdev00.sistemapedido.domain.Client;
import edu.mtdev00.sistemapedido.domain.TypeClient;
import edu.mtdev00.sistemapedido.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping(value = "/client")
public class ClientResource  {
	@Autowired
	private ClientService service;
	
	@Operation(description = "Search client by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode ="200",description  = "Return client"),
			@ApiResponse(responseCode ="400",description  = "Not Found Client")
			
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTOComplet> find(@PathVariable Long id) {
	    Client client = service.findClient(id);
	    List<Address> addresses = client.getAddress(); 
	    Address address = addresses.isEmpty() ? null : addresses.get(0); 
	    TypeClient type = TypeClient.toEnum(client.getType());
	    ClientDTOComplet clientDTO = new ClientDTOComplet(client, type.getDescricao(), address);
	    return ResponseEntity.ok(clientDTO);
	}
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPageClient(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Client> list = service.findPageClient(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDTO = list.map(client -> {
			TypeClient type = TypeClient.toEnum(client.getType());
			String description = type.getDescricao();
			return new ClientDTO(client, description); // Criar o ClientDTO com a descrição
		});
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAllClients() {
		List<Client> list = service.findAll();
		List<ClientDTO> listDto = list.stream().map(client -> {
			TypeClient type = TypeClient.toEnum(client.getType());
			return new ClientDTO(client, type.getDescricao());
		}).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTOComplet objDto) {
		Client obj = service.fromDTO(objDto);
		obj.setId(null);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Long id) {
		Client obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Client> updatePatch(@Valid @RequestBody ClientDTO objDto, @PathVariable Long id) {
		Client obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.updatePatch(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Client> delete(@RequestBody ClientDTO objDto, @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}