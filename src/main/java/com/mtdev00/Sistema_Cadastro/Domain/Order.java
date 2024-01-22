
package com.mtdev00.Sistema_Cadastro.Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instance;

	@ManyToOne
	@JoinColumn(name = "client.id")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "addres_of_delivery_id")
	private Address andressDelivery;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@OneToMany(mappedBy = "id.order")
	Set<OrderItems> items = new HashSet<>();

	public Order() {

	}

	public Order(Integer id, Date instance, Client clients, Address andressDelivery) {
		this.id = id;
		this.instance = instance;
		this.client = clients;
		this.andressDelivery = andressDelivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstance() {
		return instance;
	}

	public void setInstance(Date instance) {
		this.instance = instance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAndressDelivery() {
		return andressDelivery;
	}

	public void setAndressDelivery(Address andressDelivery) {
		this.andressDelivery = andressDelivery;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItems> getItems() {
		return items;
	}

	public void setItems(Set<OrderItems> items) {
		this.items = items;
	}

}
