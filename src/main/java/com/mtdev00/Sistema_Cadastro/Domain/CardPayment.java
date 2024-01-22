package com.mtdev00.Sistema_Cadastro.Domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
	private static final long serialVersionUID = -5724425900255155313L;
	private Integer parcelsAmount;

	public CardPayment() {

	}

	public CardPayment(Integer id, StatusPay status, Order order, Integer parcelsAmount) {
		super(id, status, order);
		this.parcelsAmount = parcelsAmount;

	}

	public Integer getParcelsAmount() {
		return parcelsAmount;
	}

	public void setParcelsAmount(Integer parcelsAmount) {
		this.parcelsAmount = parcelsAmount;
	}

}
