package com.mtdev00.Sistema_Cadastro.DTO;

import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Service.Validation.ClientUpdate;
@ClientUpdate
public class ClientDTO {
	private Integer id;
	private String name;
	private String email;
	private Integer type;
	private String cpf;
	private String cnpj;
	private String descriptionType;
	
	public ClientDTO() {
		// TODO Auto-generated constructor stub
	}
	public ClientDTO(Client obj,String description) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.type = obj.getType();
		this.cpf = obj.getCpf();
		this.cnpj = obj.getCnpj();
		this.descriptionType = description;

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}
	
}
