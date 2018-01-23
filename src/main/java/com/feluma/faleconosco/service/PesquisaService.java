package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.filter.PesquisaFilter;

public class PesquisaService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static List<PesquisaFilter> carregaListaParametrosPesquisa(Object filtro){
		List<PesquisaFilter> listaParametrosPesquisa = new ArrayList<PesquisaFilter>();
		if(filtro != null){
			String operacao = null;
			Object valor = null;
			Field[] campos = filtro.getClass().getDeclaredFields();
			
			for(int i=1; i<campos.length; i++){  
				campos[i].setAccessible(true);
				try {
					valor = campos[i].get(filtro);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if(valor != null && !valor.equals("")){
					if(valor instanceof String) operacao = "like";
					else operacao = "=";
					listaParametrosPesquisa.add(new PesquisaFilter(campos[i].getName(), valor, operacao));
				}
			}
		}
		return listaParametrosPesquisa;
	}
	
	public static StringBuffer testarCamposPesquisaUsuario(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("perfil")){
				sb.append(" and per.codigo = ");
				sb.append(((Perfil) objPesquisa.getValor()).getCodigo());
			}
			if(objPesquisa.getCampo().equals("nome")){
				sb.append(" and usu.nome like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
			if(objPesquisa.getCampo().equals("cpf")){
				sb.append(" and usu.cpf = ");
				sb.append("'"+objPesquisa.getValor()+"'");
			}
			if(objPesquisa.getCampo().equals("email")){
				sb.append(" and usu.email like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}

	public static StringBuffer testarCamposPesquisaSetor(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("unidade")){
				sb.append(" and uni.codigo = ");
				sb.append(((Unidade) objPesquisa.getValor()).getCodigo());
			}
			if(objPesquisa.getCampo().equals("descricao")){
				sb.append(" and seto.descricao like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}

	public static StringBuffer testarCamposPesquisaAvaliacao(StringBuffer sb, List<PesquisaFilter> listaParamentrosPesquisa) {
		for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
			if(objPesquisa.getCampo().equals("statusAvaliacao")){
				sb.append(" and ava.statusAvaliacao = ");
				sb.append("'"+objPesquisa.getValor()+"'");
			}
			if(objPesquisa.getCampo().equals("nomeCliente")){
				sb.append(" and cli.nome like ");
				sb.append("'%"+objPesquisa.getValor()+"%'");
			}
		}
		return sb;
	}
	
}