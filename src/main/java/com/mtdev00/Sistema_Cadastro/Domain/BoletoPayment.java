package com.mtdev00.Sistema_Cadastro.Domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

@Entity
@JsonTypeName("boletoPayment")
public class BoletoPayment extends Payment {
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "DD/mm/yyyy HH:MM")
	private Date dueDate;
	@JsonFormat(pattern = "DD/mm/yyyy HH:MM")
	private Date paymentDate;

	public BoletoPayment() {
	}

	public BoletoPayment(Long id, StatusPay status, Order order, Date paymentDate, Date dueDate) {
		super(id, status, order);
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;

	}

	@JsonIgnore
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@JsonIgnore
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
