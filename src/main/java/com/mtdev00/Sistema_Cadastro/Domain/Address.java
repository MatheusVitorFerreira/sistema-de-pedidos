package com.mtdev00.Sistema_Cadastro.Domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
@Entity
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String streat;
	@NotEmpty
	private String addressSupplement;
	@NotEmpty
	private String cep;
	@NotEmpty
	private String city;
	@JsonManagedReference
	@ManyToOne()
	@JoinColumn(name = "clients_id")
	private Client client;

	public Address() {
	}
	public Address(Integer id, String streat, String addressSupplement, String cep, String city,Client clients) {
		super();
		this.id = id;
		this.streat = streat;
		this.addressSupplement = addressSupplement;
		this.setCep(cep);
		this.city = city;
		this.setClients(clients);
	}

	public String getStreat() {
		return streat;
	}

	public void setStreat(String streat) {
		this.streat = streat;
	}

	public String getAddressSupplement() {
		return addressSupplement;
	}

	public void setAddressSupplement(String addressSupplement) {
		this.addressSupplement = addressSupplement;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClients() {
		return client;
	}
	public void setClients(Client clients) {
		this.client = clients;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
	
}
