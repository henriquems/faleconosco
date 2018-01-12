package com.feluma.faleconosco.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.service.UsuarioService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MeusDadosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void inicializar(){
		if(!FacesUtil.isPostback()){
			usuario = usuarioService.recuperarUsuario(usuarioLogado.getUsuario().getCodigo());
		}
	}
	
	public void verificarEmail(){
		if(usuarioService.verificarEmail(usuario.getEmail()) != 0){
			FacesUtil.addErroMessage("Já existe um usuário cadastrado com o e-mail "+usuario.getEmail());
		}
	}
	
	public void salvar(){
		usuario = usuarioService.salvar(this.usuario);
		FacesUtil.addInfoMessage("Dados do usuário "+usuario.getNome()+" salvo com sucesso!");
	}

}