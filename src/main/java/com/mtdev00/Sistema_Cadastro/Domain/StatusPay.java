package com.mtdev00.Sistema_Cadastro.Domain;

public enum StatusPay {
	PENDANT(1, "Pendente"), PAY(2, "Pago"), OVERDUE(3, "Vencido"), CANCELED(4, "Cancelado");

	private int cod;
	private String description;

	private StatusPay(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static StatusPay toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (StatusPay x: StatusPay.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("ID Invalid" + cod);
	}
}
