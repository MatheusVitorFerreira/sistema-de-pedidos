package com.mtdev00.Sistema_Cadastro.Service.Validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mtdev00.Sistema_Cadastro.DTO.ClientDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.FieldMessage;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.UpdateException;
import com.mtdev00.Sistema_Cadastro.repository.ClientRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	@Autowired
	private ClientRepository clientRepo;

	@SuppressWarnings("unused")
	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClientUpdate ann) {
	}
	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getEmail() == null || objDto.getEmail().isEmpty()) {
			list.add(new FieldMessage("email", "Email é Obrigatório"));
		} else {
			Client existingClient = clientRepo.findByEmail(objDto.getEmail());
			if (existingClient != null && existingClient.getId() != objDto.getId()) {
				list.add(new FieldMessage("email", "Email já cadastrado para outro cliente"));
			}
		}

		if (!list.isEmpty()) {
			throw new UpdateException(list); // Lance a exceção UpdateException com a lista de erros
		}
		return list.isEmpty();
	}
}

