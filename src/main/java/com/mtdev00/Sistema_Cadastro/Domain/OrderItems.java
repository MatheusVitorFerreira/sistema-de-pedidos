package com.mtdev00.Sistema_Cadastro.Domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name ="Item orders")
public class OrderItems implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private ItemOrderPK id = new ItemOrderPK();
	private Integer quantity;
	private Double price;
	private Double discount;
	public OrderItems() {

	}
	public OrderItems(Order order, Product product, Integer quantity, Double price, Double discount) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}
	public Product getProducto() {
		return id.getProduct();
	}
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}
	public ItemOrderPK getId() {
		return id;
	}

	public void setId(ItemOrderPK id) {
		this.id = id;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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
		OrderItems other = (OrderItems) obj;
		return Objects.equals(id, other.id);
	}
}
