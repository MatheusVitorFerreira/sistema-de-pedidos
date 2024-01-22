package com.mtdev00.Sistema_Cadastro.Service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtdev00.Sistema_Cadastro.DTO.ClientDTO;
import com.mtdev00.Sistema_Cadastro.DTO.ClientDTOComplet;
import com.mtdev00.Sistema_Cadastro.Domain.Address;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Domain.TypeClient;
import com.mtdev00.Sistema_Cadastro.repository.AddressRepository;
import com.mtdev00.Sistema_Cadastro.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	AddressRepository addresRepo;

	public Client findClient(Integer id) {
		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id,
				"Object not found!! ID: " + id + ", Type: " + Client.class.getName()));
	}
	@Transactional
	public Client insert(Client obj) {
		obj= clientRepo.save(obj);
		addresRepo.saveAll(obj.getAddress());
		return obj;
	}
	public Client fromDTO(ClientDTO objDto) {
	    return new Client(
	    		objDto.getId(),
	    		objDto.getName(),
				objDto.getEmail(),
				TypeClient.toEnum(objDto.getType()),
				objDto.getCpf(),
				objDto.getCnpj());
	}
	
	public Client fromDTO(ClientDTOComplet objDto) {
	    TypeClient type = TypeClient.toEnum(objDto.getType());
	    String cpf = type.equals(TypeClient.FISICA) ? objDto.getCpf() :null;
	    String cnpj = type.equals(TypeClient.JURIDICA) ? objDto.getCnpj() : null;
	    Client cli = new Client(null, objDto.getName(), objDto.getEmail(), type, cpf, cnpj);

	    Address addr = new Address(objDto.getId(), objDto.getStreat(), objDto.getAddressSupplement(), 
	                             objDto.getCep(), objDto.getCity(), cli);
	    cli.getAddress().add(addr);

	    cli.getTelephone().add(objDto.getTelephone1());
	    if (objDto.getTelephone2() != null) {
	        cli.getTelephone().add(objDto.getTelephone2());
	    }

	    return cli;
	}
	
}
