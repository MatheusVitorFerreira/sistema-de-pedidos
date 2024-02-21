package com.mtdev00.Sistema_Cadastro.Resourche;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.mtdev00.Sistema_Cadastro.DTO.ProductDTO;
import com.mtdev00.Sistema_Cadastro.Domain.Product;
import com.mtdev00.Sistema_Cadastro.Service.ProductService;
import com.mtdev00.Sistema_Cadastro.Service.URL;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {


	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findProduct(@PathVariable Long id) {
		Product obj = service.findProduct(id);
		ProductDTO dto = new ProductDTO(obj);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductDTO>> findPageClient(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		@SuppressWarnings("unused")
		String nomeDecode = URL.decodeParam(name);
		Page<Product> list = service.FindPageProduct(page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDTO = list.map(product -> new ProductDTO(product));

		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findall() {
		List<Product> list = service.findAll();
		List<ProductDTO> listDto = list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAllProducts() {
		List<Product> list = service.findAll();
		List<ProductDTO> listDto = list.stream().map(products -> new ProductDTO(products)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ProductDTO objDto) {
		Product obj = service.fromDTO(objDto);
		obj.setId(null);
		obj = service.insertProduct(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductDTO objDto, @PathVariable Long id) {
		Product obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Product> updatePatch(@Valid @RequestBody ProductDTO objDto, @PathVariable Long id) {
		Product obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.updatePatch(obj);
		return ResponseEntity.noContent().build();
	}

}
