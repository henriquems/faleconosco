package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.AvaliacaoDAO;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.UnidadeSetor;

@FacesConverter(forClass=Avaliacao.class)
public class AvaliacaoConverter implements Converter {

	@Inject
	private AvaliacaoDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Avaliacao retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.recuperarAvaliacao(id);
			
			if(retorno.getUnidadeSetor() == null){
				retorno.setUnidadeSetor(new UnidadeSetor());
			}
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Avaliacao avaliacao = (Avaliacao) value;
			return avaliacao.getCodigo() == null ? null : avaliacao.getCodigo().toString();
		}
		
		return "";
	}

}