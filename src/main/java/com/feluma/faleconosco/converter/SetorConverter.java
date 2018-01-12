package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.SetorDAO;
import com.feluma.faleconosco.model.Setor;

@FacesConverter(forClass=Setor.class)
public class SetorConverter implements Converter {

	@Inject
	private SetorDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Setor retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Setor setor = (Setor) value;
			return setor.getCodigo() == null ? null : setor.getCodigo().toString();
		}
		
		return "";
	}

}