package com.mtdev00.Sistema_Cadastro.Resourche;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mtdev00.Sistema_Cadastro.DTO.ClientDTO;
import com.mtdev00.Sistema_Cadastro.DTO.ClientDTOComplet;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Domain.TypeClient;
import com.mtdev00.Sistema_Cadastro.Service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	@Autowired
	private ClientService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> find(@PathVariable Integer id){
		Client client = service.findClient(id);
		TypeClient type = TypeClient.toEnum(client.getType());
		ClientDTO clientDTO = new ClientDTO(client, type.getDescricao());
		return ResponseEntity.ok(clientDTO);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClientDTOComplet objDto) {
		Client obj = service.fromDTO(objDto);
		obj.setId(null);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	/*@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@RequestBody ClientDTO objDto,@PathVariable Integer id) {
		
	}*/

}
