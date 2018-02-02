package com.feluma.faleconosco.filter;

import java.io.Serializable;

import com.feluma.faleconosco.model.Unidade;

public class SetorFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Unidade unidade;
	private String descricao;
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
