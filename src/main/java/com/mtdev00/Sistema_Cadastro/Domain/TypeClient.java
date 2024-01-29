package com.mtdev00.Sistema_Cadastro.Domain;

public enum TypeClient {
	FISICA(1, "Pessoa Física"), JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TypeClient() {

	}

	private TypeClient(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TypeClient toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TypeClient x : TypeClient.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID Invalid " + cod);
	}

}
