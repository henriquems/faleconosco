package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_usu")
	private Long codigo;
	
	@NotEmpty
	@Column(name="cpf_usu")
	private String cpf;

	@NotEmpty
	@Column(name="ema_usu")
	private String email;
	
	@NotEmpty
	@Column(name="nom_usu")
	private String nome;

	@Column(name="sen_usu")
	private String senha;
	
	@NotNull
	@Column(name="sta_usu")
	@Enumerated(EnumType.STRING)
	private StatusUsuario statusUsuario;

	//bi-directional many-to-one association to Avaliacao
	@OneToMany(mappedBy="usuario")
	private List<Avaliacao> avaliacoes;

	//bi-directional many-to-one association to MensagemOriginal
	@OneToMany(mappedBy="usuario")
	private List<MensagemOriginal> mensagensOriginais;

	//bi-directional many-to-one association to Resposta
	@OneToMany(mappedBy="usuario")
	private List<Resposta> respostas;

	//bi-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(
		name="usuario_perfil"
		, joinColumns={
			@JoinColumn(name="cod_usu")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cod_per")
			}
		)
	private Set<Perfil> perfis = new HashSet<Perfil>();
	
	//bi-directional many-to-one association to UnidadeSetor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cod_set", referencedColumnName="cod_set"),
		@JoinColumn(name="cod_uni", referencedColumnName="cod_uni")
		})
	private UnidadeSetor unidadeSetor = new UnidadeSetor();

	public Usuario() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public List<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Avaliacao addAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().add(avaliacao);
		avaliacao.setUsuario(this);

		return avaliacao;
	}

	public Avaliacao removeAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().remove(avaliacao);
		avaliacao.setUsuario(null);

		return avaliacao;
	}

	public List<MensagemOriginal> getMensagemOriginais() {
		return this.mensagensOriginais;
	}

	public void setMensagemOriginais(List<MensagemOriginal> mensagensOriginais) {
		this.mensagensOriginais = mensagensOriginais;
	}

	public MensagemOriginal addMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagemOriginais().add(mensagemOriginal);
		mensagemOriginal.setUsuario(this);

		return mensagemOriginal;
	}

	public MensagemOriginal removeMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagemOriginais().remove(mensagemOriginal);
		mensagemOriginal.setUsuario(null);

		return mensagemOriginal;
	}

	public List<Resposta> getRespostas() {
		return this.respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Resposta addResposta(Resposta resposta) {
		getRespostas().add(resposta);
		resposta.setUsuario(this);

		return resposta;
	}

	public Resposta removeResposta(Resposta resposta) {
		getRespostas().remove(resposta);
		resposta.setUsuario(null);

		return resposta;
	}

	public Set<Perfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfils(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public UnidadeSetor getUnidadeSetor() {
		return unidadeSetor;
	}

	public void setUnidadeSetor(UnidadeSetor unidadeSetor) {
		this.unidadeSetor = unidadeSetor;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}