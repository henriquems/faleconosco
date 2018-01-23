package com.feluma.faleconosco.filter;

import java.io.Serializable;

import com.feluma.faleconosco.model.StatusAvaliacao;

public class AvaliacaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private StatusAvaliacao statusAvaliacao;
	private String nomeCliente;

	public StatusAvaliacao getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
