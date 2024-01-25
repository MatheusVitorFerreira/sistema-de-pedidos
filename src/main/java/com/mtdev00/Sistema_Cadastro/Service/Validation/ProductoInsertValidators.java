package com.mtdev00.Sistema_Cadastro.Service.Validation;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mtdev00.Sistema_Cadastro.DTO.ProductDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.FieldMessage;
import com.mtdev00.Sistema_Cadastro.repository.ProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductoInsertValidators implements ConstraintValidator<InsertProduct, ProductDTO> {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public void initialize(InsertProduct ann) {
	}
	
	@Override
	public boolean isValid(ProductDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		 if(objDto.getName() == null || objDto.getName().isEmpty()) {
			 list.add(new FieldMessage("Name", "Name é Obrigatório"));
		
		}else {
			@SuppressWarnings("unused")
			Client existingClient = (Client) productRepo.findByNameIgnoreCaseContaining(objDto.getName());
		}
		 
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
