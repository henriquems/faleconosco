package com.feluma.faleconosco.filter;

public class PesquisaFilter {
	
	private String campo;
	private Object valor;
	private String operacao;
	
	public PesquisaFilter(){}
	
	public PesquisaFilter(String campo, Object valor, String operacao){
		this.campo = campo;
		this.valor = valor;
		this.operacao = operacao;
	}
	
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	
}
