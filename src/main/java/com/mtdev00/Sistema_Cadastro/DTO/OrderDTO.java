package com.mtdev00.Sistema_Cadastro.DTO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtdev00.Sistema_Cadastro.Domain.Client;
import com.mtdev00.Sistema_Cadastro.Domain.Order;
import com.mtdev00.Sistema_Cadastro.Domain.OrderItems;
import com.mtdev00.Sistema_Cadastro.Domain.StatusPay;

public class OrderDTO {
	private Integer id;
	private String clientName;
	private String addressStreet;
	private String addressCep;
	private String addressCity;
	private StatusPay paymentStatus;
	private List<OrderItems> items;
	@JsonIgnore
	private AddressDTO address;
	private Double amountTotal;

	public OrderDTO(Order order, Client cliente, AddressDTO address,StatusPay paymentStatus) {
		this.id = order.getId();
		this.clientName = cliente.getName();
		this.addressStreet = address.getStreet();
		this.addressCep = address.getCep();
		this.addressCity = address.getCity();
		this.items = new ArrayList<>(order.getItems());
		this.paymentStatus = paymentStatus;
		this.amountTotal = order.getAmountTotal();
	}

	public OrderDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCep() {
		return addressCep;
	}

	public void setAddressCep(String addressCep) {
		this.addressCep = addressCep;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public List<OrderItems> getItems() {
		return items;
	}

	public void setItems(List<OrderItems> items) {
		this.items = items;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public StatusPay getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(StatusPay paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

}