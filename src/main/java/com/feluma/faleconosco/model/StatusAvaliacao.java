package com.feluma.faleconosco.model;

public enum StatusAvaliacao {
	
	ENVIADOPELOCLIENTE("ENVIADO PELO CLIENTE", "#0000FF"),
	AVALIADOPELOOUVIDOR("AVALIADO PELO OUVIDOR", "#006400"),
	RESPONDIDOPELOGERENTE("RESPONDIDO PELO GERENTE", "#FF8C00"),
	RESPONDIDOPELOOUVIDOR("RESPONDIDO PELO OUVIDOR", "#9afcfd"),
	REJEITADOPELOGERENTE("REJEITADO PELO GERENTE", "#fe0c00");
	
	private String descricao;
	private String cor;
	
	private StatusAvaliacao(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCor() {
		return cor;
	}
	
}
