package com.mtdev00.Sistema_Cadastro.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtdev00.Sistema_Cadastro.Domain.Address;
import com.mtdev00.Sistema_Cadastro.Domain.Client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ClientDTOComplet implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "FIELD REQUIRED")
	private String name;
	@NotEmpty(message = "FIELD REQUIRED")
	@Email
	private String email;
	@NotNull
	private Integer type;
	@NotEmpty(message = "FIELD REQUIRED")
	private String cpf;
	@NotEmpty(message = "FIELD REQUIRED")
	private String cnpj;
	@NotEmpty(message = "FIELD REQUIRED")
	private String descriptionType;
	@JsonIgnore
	private Address ad;
	@NotEmpty(message = "FIELD REQUIRED")
	private String city;
	@NotEmpty(message = "FIELD REQUIRED")
	private String street;
	@NotEmpty(message = "FIELD REQUIRED")
	private String addressSupplement;
	@NotEmpty(message = "FIELD REQUIRED")
	private String cep;
	@NotEmpty(message = "FIELD REQUIRED")
	private String telephone1;

	public ClientDTOComplet(Client client, String description, Address address) {
		name = client.getName();
		email = client.getEmail();
		type = client.getType();
		cpf = client.getCpf();
		cnpj = client.getCnpj();
		descriptionType = description;
		this.setAd(address);
		if (address != null) {
			city = address.getCity();
			street = address.getStreet();
			addressSupplement = address.getAddressSupplement();
			cep = address.getCep();
		}
		telephone1 = client.getTelephone().iterator().next();
	}

	public ClientDTOComplet() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public void setTypeClient(String typeClient) {
		this.descriptionType = typeClient;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressSupplement() {
		return addressSupplement;
	}

	public void setAddressSupplement(String addressSupplement) {
		this.addressSupplement = addressSupplement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public Address getAd() {
		return ad;
	}

	public void setAd(Address ad) {
		this.ad = ad;
	}
}
