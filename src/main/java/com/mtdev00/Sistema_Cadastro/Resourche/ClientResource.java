package com.mtdev00.Sistema_Cadastro.Resourche;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mtdev00.Sistema_Cadastro.DTO.ClientDTO;
import com.mtdev00.Sistema_Cadastro.DTO.ClientDTOComplet;
import com.mtdev00.Sistema_Cadastro.Domain.Address;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Domain.TypeClient;
import com.mtdev00.Sistema_Cadastro.Service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {
	@Autowired
	private ClientService service;

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