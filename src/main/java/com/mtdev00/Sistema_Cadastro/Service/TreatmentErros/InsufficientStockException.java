package com.mtdev00.Sistema_Cadastro.Service.TreatmentErros;

public class InsufficientStockException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientStockException(String msg) {
		super(msg);
	}

	public InsufficientStockException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
