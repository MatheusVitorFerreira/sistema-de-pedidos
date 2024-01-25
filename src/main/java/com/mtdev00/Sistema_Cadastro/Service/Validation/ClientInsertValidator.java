package com.mtdev00.Sistema_Cadastro.Service.Validation;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mtdev00.Sistema_Cadastro.DTO.ClientDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.FieldMessage;
import com.mtdev00.Sistema_Cadastro.repository.ClientRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<InsertClient, ClientDTO> {
	
	@Autowired
	private ClientRepository ClientRepo;
	
	@Override
	public void initialize(InsertClient ann) {
	}
	
	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		 if (objDto.getEmail() == null || objDto.getEmail().isEmpty()) {
		        list.add(new FieldMessage("email", "Email é Obrigatório"));
		    } else {
		        Client existingClient = ClientRepo.findByEmail(objDto.getEmail());
		        if (existingClient != null) {
		            list.add(new FieldMessage("email", "Email já existe"));
		        }
		    }
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
