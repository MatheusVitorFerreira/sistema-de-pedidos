package com.mtdev00.Sistema_Cadastro.Domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
	private static final long serialVersionUID = -5724425900255155313L;
	@NotNull
	private Integer parcelsAmount;

	public CardPayment() {

	}

	public CardPayment(Long id, StatusPay status, Order order, Integer parcelsAmount) {
		super(id, status, order);
		this.parcelsAmount = parcelsAmount;

	}

	public Integer getParcelsAmount() {
		return parcelsAmount;
	}

	public void setParcelsAmount(Integer parcelsAmount) {
		this.parcelsAmount = parcelsAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("parcelsAmount:");
		builder.append(getParcelsAmount());
		return builder.toString();
	}

}
