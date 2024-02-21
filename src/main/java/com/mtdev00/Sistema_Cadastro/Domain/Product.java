package com.mtdev00.Sistema_Cadastro.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "Price")
	private Double price;
	private Integer stockQuantity;
	private String category;
	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItems> items = new HashSet<>();

	public Product() {

	}

	public Product(Long id, String name, Double price, Integer stockQuantity, String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.category = category;
	}

	@JsonIgnore
	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();
		for (OrderItems x : items) {
			list.add(x.getOrder());
		}
		return list;
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

	public Set<OrderItems> getItems() {
		return items;
	}

	public void setItems(Set<OrderItems> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
