package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.model.StatusUsuario;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.service.PerfilService;
import com.feluma.faleconosco.service.SetorService;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.service.UsuarioService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private PerfilService perfilService;
	
	@Inject
	private UnidadeService unidadeService;
	
	@Inject
	private SetorService setorService;
	
	private Usuario usuario;
	private Perfil perfil;
	private List<Perfil> perfis;
	private List<StatusUsuario> statusUsuarios;
	private List<Unidade> unidades;
	private List<Setor> setores;
	
	public CadastroUsuarioBean() {
		limpar();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public List<StatusUsuario> getStatusUsuarios() {
		return statusUsuarios;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			perfis = perfilService.listar();
			unidades = unidadeService.listar();
			statusUsuarios = Arrays.asList(StatusUsuario.values());
		}
	}
	
	public void recuperarSetoresDaUnidade(){
		if(usuario.getUnidadeSetor().getId().getUnidade() != null){
			setores = setorService.recuperarSetoresDaUnidade(usuario.getUnidadeSetor().getId().getUnidade().getCodigo());
		}
	}
	
	public String salvar(){
		String retorno = null;
		if(usuario.getPerfis().size() != 0){
			
			FacesUtil.addInfoMessage("Usuário "+usuario.getNome()+" salvo com sucesso!");
			return "pesquisaUsuario.xhtml?faces-redirect=true";
		} else {
			FacesUtil.addErroMessage("Favor adicionar um perfil para o usuário.");
		}
		return retorno;
	}
	
	public void limpar(){
		usuario = new Usuario();
		perfil = new Perfil();
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(usuario.getCodigo() != null){
			editando = true;
			usuario = usuarioService.recuperarUsuario(usuario.getCodigo());
			recuperarSetoresDaUnidade();
		}	
		return editando;
	}
	
	public void adicionarPerfil(){
		if(perfil != null){
			if(this.usuario.getPerfis().contains(this.perfil)){
				FacesUtil.addErroMessage("O perfil selecionado já se encontra adicionado!");
			} else {
				this.usuario.getPerfis().add(this.perfil);
				FacesUtil.addAlertMessage("Perfil adicionado com sucesso! Favor salvar os dados!");
			}
		} else {
			FacesUtil.addErroMessage("Favor selecionar um perfil!");
		}
	}
	
	public void excluirPerfil(Perfil perfil){
		this.usuario.getPerfis().remove(perfil);
	}
	
}