package com.feluma.faleconosco.model;

public enum TipoAssunto {
	
	DUVIDA("Dúvida"),
	SUGESTAO("Sugestão"),
	RELCAMACAO("Reclamação"),
	OUTROS("Outros");
	
	private String descricao;
	
	private TipoAssunto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
