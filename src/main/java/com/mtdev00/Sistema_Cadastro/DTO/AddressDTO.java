package com.mtdev00.Sistema_Cadastro.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mtdev00.Sistema_Cadastro.Domain.Address;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = -6750275615195036779L;
    private Integer id;
    private String street; 
    private String addressSupplement;
    private String cep;
    private String city;

    public AddressDTO(Address ad) {
        id = ad.getId();
        street = ad.getStreet();
        addressSupplement = ad.getAddressSupplement();
        cep = ad.getCep();
        city = ad.getCity();
    }

    public AddressDTO() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreat(String street) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
