package com.mtdev00.Sistema_Cadastro.Service.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mtdev00.Sistema_Cadastro.DTO.ProductDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Product;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.FieldMessage;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.UpdateException;
import com.mtdev00.Sistema_Cadastro.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductUpdateValidator implements ConstraintValidator<ProductUpdate,ProductDTO> {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private HttpServletRequest request; //Essa função faz com que eu consiga navegar pela URI da HTTP
	
	@Override
	public void initialize(ProductUpdate ann) {
	}
	
	@Override
	public boolean isValid(ProductDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();

		List<Product> allProducts = productRepo.findAll();

		for (Product existingProduct: allProducts) {
			if (existingProduct.getName().equalsIgnoreCase(objDto.getName()) && !existingProduct.getId().equals(id)) {
				list.add(new FieldMessage("name", "Nome já existente para outro produto"));
				break;
			}
		}

		if (!list.isEmpty()) {
		    throw new UpdateException(list);
		}
		return list.isEmpty();
	}

}
