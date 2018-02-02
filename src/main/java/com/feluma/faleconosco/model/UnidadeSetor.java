package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidade_setor database table.
 * 
 */
@Entity
@Table(name="unidade_setor")
@NamedQuery(name="UnidadeSetor.findAll", query="SELECT u FROM UnidadeSetor u")
public class UnidadeSetor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_uni_set")
	private Long codigo;

	//bi-directional many-to-one association to Avaliacao
	@OneToMany(mappedBy="unidadeSetor")
	private List<Avaliacao> avaliacoes;

	//bi-directional many-to-one association to Setor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_set")
	private Setor setor = new Setor();

	//bi-directional many-to-one association to Unidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_uni")
	private Unidade unidade;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="unidadeSetor")
	private List<Usuario> usuarios;

	public UnidadeSetor() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Avaliacao addAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().add(avaliacao);
		avaliacao.setUnidadeSetor(this);

		return avaliacao;
	}

	public Avaliacao removeAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().remove(avaliacao);
		avaliacao.setUnidadeSetor(null);

		return avaliacao;
	}

	public Setor getSetor() {
		return this.setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUnidadeSetor(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUnidadeSetor(null);

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeSetor other = (UnidadeSetor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}