package com.mtdev00.Sistema_Cadastro.DTO;

import java.io.Serializable;

import com.mtdev00.Sistema_Cadastro.Domain.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Double price;
	@NotNull
	private Integer stockQuantity;
	@NotBlank
	private String category;

	public ProductDTO() {
	}

	public ProductDTO(Product objDto) {
		super();
		this.id = objDto.getId();
		this.name = objDto.getName();
		this.price = objDto.getPrice();
		this.stockQuantity = objDto.getStockQuantity();
		this.category = objDto.getCategory();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
