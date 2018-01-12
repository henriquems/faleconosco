package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.UnidadeDAO;
import com.feluma.faleconosco.model.Unidade;

@FacesConverter(forClass=Unidade.class)
public class UnidadeConverter implements Converter {

	@Inject
	private UnidadeDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Unidade retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Unidade unidade = (Unidade) value;
			return unidade.getCodigo() == null ? null : unidade.getCodigo().toString();
		}
		
		return "";
	}

}