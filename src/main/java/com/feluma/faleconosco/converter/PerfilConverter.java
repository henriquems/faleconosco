package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.PerfilDAO;
import com.feluma.faleconosco.model.Perfil;

@FacesConverter(forClass=Perfil.class)
public class PerfilConverter implements Converter {

	@Inject
	private PerfilDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Perfil retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.pesquisarPorCodigo(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Perfil perfil = (Perfil) value;
			return perfil.getCodigo() == null ? null : perfil.getCodigo().toString();
		}
		
		return "";
	}

}