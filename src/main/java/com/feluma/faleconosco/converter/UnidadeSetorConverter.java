package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.UnidadeSetorDAO;
import com.feluma.faleconosco.model.UnidadeSetor;

@FacesConverter(forClass=UnidadeSetor.class)
public class UnidadeSetorConverter implements Converter {

	@Inject
	private UnidadeSetorDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UnidadeSetor retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			UnidadeSetor unidadeSetor = (UnidadeSetor) value;
			return unidadeSetor.getCodigo() == null ? null : unidadeSetor.getCodigo().toString();
		}
		
		return "";
	}

}