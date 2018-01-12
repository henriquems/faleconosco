package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.filter.UsuarioFilter;
import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.service.PerfilService;
import com.feluma.faleconosco.service.UsuarioService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private PerfilService perfilService;
	
	private List<Usuario> usuarios;
	
	private Usuario usuario;
	private UsuarioFilter filtro;
	private List<Perfil> perfis;
	
	public PesquisaUsuarioBean(){
		filtro = new UsuarioFilter();
	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}
	
	public void excluir(Usuario usuario){
		usuarioService.excluir(usuario);
		usuarios.remove(usuario);
		FacesUtil.addInfoMessage("Usuário "+usuario.getNome()+" excluído com sucesso.");
	}
	
	public void pesquisar(){
		usuarios = usuarioService.pesquisarUsuario(filtro);
	}
	
	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			usuarios = usuarioService.listarUsuario();
			perfis = perfilService.listar();
		}
	}
	
	public void buscarPerfisDoUsuario(){
		usuario = usuarioService.buscarPerfisDoUsuario(usuario.getCodigo());
	}
	
}