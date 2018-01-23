package com.feluma.faleconosco.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.feluma.faleconosco.dao.RespostaDAO;
import com.feluma.faleconosco.model.Resposta;

@FacesConverter(forClass=Resposta.class)
public class RespostaConverter implements Converter {

	@Inject
	private RespostaDAO dao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Resposta retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.recuperarResposta(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Resposta perfil = (Resposta) value;
			return perfil.getCodigo() == null ? null : perfil.getCodigo().toString();
		}
		
		return "";
	}

}