package com.mtdev00.Sistema_Cadastro.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mtdev00.Sistema_Cadastro.DTO.ProductDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Product;
import com.mtdev00.Sistema_Cadastro.Service.TreatmentErros.InsufficientStockException;
import com.mtdev00.Sistema_Cadastro.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	

	public Product findProduct(@PathVariable Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id, "ID: " + id + " Not Found"));
	}

	public Page<Product> FindPageProduct(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return productRepository.findAll(pageRequest);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product fromDTO(ProductDTO objDto) {
		Product prod = new Product(objDto.getId(), objDto.getName(), objDto.getPrice(), objDto.getStockQuantity(),
				objDto.getCategory());
		return prod;
	}

	@Transactional
	public Product insertProduct(Product obj) {
		obj = productRepository.save(obj);
		return obj;
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product update(Product obj) {
		Product newObj = findProduct(obj.getId());
		updateData(newObj, obj);
		return productRepository.save(newObj);
	}

	public void updateData(Product newObj, Product objDto) {
		newObj.setName(objDto.getName());
		newObj.setPrice(objDto.getPrice());
		newObj.setStockQuantity(objDto.getStockQuantity());
		newObj.setCategory(objDto.getCategory());
	}

	public void delete(Long id) {
		findProduct(id);
		productRepository.deleteById(id);
	}

	public Product updatePatch(Product obj) {
		Product existingProduct = productRepository.findById(obj.getId()).orElse(null);
		if (existingProduct == null) {
			return null;
		}
		if (obj.getName() != null) {
			existingProduct.setName(obj.getName());
		}
		if (obj.getPrice() != null) {
			existingProduct.setPrice(obj.getPrice());
		}
		if (obj.getStockQuantity() != null) {
			existingProduct.setStockQuantity(obj.getStockQuantity());
		}
		if (obj.getCategory() != null) {
			existingProduct.setCategory(obj.getCategory());
		}

		return productRepository.save(existingProduct);
	}
	@Transactional
	public Integer updateStockQuantity(Long productId, Integer quantity) {
	    Product product = findProduct(productId);
	    if (product == null) {
	        throw new ObjectNotFoundException(productId, "Product not found");
	    }
	    if (product.getStockQuantity() < quantity) {
	        throw new InsufficientStockException("There is no such quantity of product in stock!" + product.getName());
	    }
	    product.setStockQuantity(product.getStockQuantity() - quantity);
	    productRepository.save(product);
	    return product.getStockQuantity();
	}
}