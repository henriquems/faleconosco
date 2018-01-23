package com.feluma.faleconosco.util.relatorio;

import java.io.Serializable;

public class DescricaoValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private Long valor;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
