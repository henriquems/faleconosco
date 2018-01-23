package com.feluma.faleconosco.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Usuario;

@Named
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}
	
	public Usuario getUsuario() {
		Usuario usuario = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			usuario = usuarioLogado.getUsuario();
		}
		
		return usuario;
	}
	
	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public boolean isAdministrador(){
		boolean retorno = false;
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			for(Perfil perfil : usuarioLogado.getUsuario().getPerfis()){
				if(perfil.getNome().equals("ADMINISTRADOR")){
					retorno = true;
					break;
				}
			}
		}
		
		return retorno;
	}
	
}
