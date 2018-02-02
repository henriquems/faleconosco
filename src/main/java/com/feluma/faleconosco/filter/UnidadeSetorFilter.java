package com.feluma.faleconosco.filter;

import java.io.Serializable;

import com.feluma.faleconosco.model.Unidade;

public class UnidadeSetorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Unidade unidade;
	private String descricaoSetor;
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getDescricaoSetor() {
		return descricaoSetor;
	}

	public void setDescricaoSetor(String descricaoSetor) {
		this.descricaoSetor = descricaoSetor;
	}

}
