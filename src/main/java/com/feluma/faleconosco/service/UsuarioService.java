package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.omnifaces.util.Utils;

import com.feluma.faleconosco.dao.UsuarioDAO;
import com.feluma.faleconosco.filter.UsuarioFilter;
import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.security.Criptografia;
import com.feluma.faleconosco.security.GeraSenha;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO dao;
	
	public List<Usuario> listar(){
		return dao.listar();
	}
	
	public Usuario pesquisarPorCodigo(Long codigo){
		return dao.pesquisarPorCodigo(codigo);
	}
	
	public List<Usuario> pesquisar(UsuarioFilter filtro){
		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
	public Usuario recuperarUsuario(Long codigo) {
		return dao.recuperarUsuario(codigo);
	}

	public Long verificarCPF(String email) {
		return dao.verificarCPF(email);
	}

	public Long verificarEmail(String email) {
		return dao.verificarEmail(email);
	}
	
	public Usuario buscarPerfisDoUsuario(Long codigo) {
		return dao.buscarPerfisDoUsuario(codigo);
	}
	
	public Usuario recuperarUsuarioPorCpf(String cpf) {
		return dao.recuperarUsuarioPorCpf(cpf);
	}
	
	public Usuario criptografarSenha(Usuario usuario){
		if(usuario.getCodigo() != null && Utils.isAnyEmpty(usuario.getSenha())){
			usuario.setSenha(dao.pesquisarPorCodigo(usuario.getCodigo()).getSenha());
		} else {
			usuario.setSenha(Criptografia.criptografarSHA1(usuario.getSenha()));
		}
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario){
		try {
			return dao.salvar(criptografarSenha(usuario));
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}
	
	public Usuario excluir(Usuario usuario) {
		try {
			return dao.excluir(usuario.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("O registro não pode ser excluído");
		}
	}

	public String restaurarSenha(Usuario usuario) {
		String novaSenha = GeraSenha.gerarSenhaAleatoria();
		usuario.setSenha(Criptografia.criptografarSHA1(novaSenha));
		
		try {
			dao.restaurarSenha(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return novaSenha;
	}

	public List<Usuario> listarUsuario() {
		return dao.listarUsuario();
	}

	public List<Usuario> pesquisarUsuario(UsuarioFilter filtro) {
		return dao.pesquisarUsuario(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}
	
}
