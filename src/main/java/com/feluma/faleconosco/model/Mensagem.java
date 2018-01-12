package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the mensagem database table.
 * 
 */
@Entity
@Table(name="mensagem")
@NamedQuery(name="Mensagem.findAll", query="SELECT m FROM Mensagem m")
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_men")
	private Long codigo;
	
	@NotNull
	@Column(name="ass_men")
	@Enumerated(EnumType.STRING)
	private TipoAssunto tipoAssunto;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_men")
	private Date data;
	
	@NotEmpty
	@Column(name="des_men")
	private String descricao;

	//bi-directional many-to-one association to Avaliacao
	@OneToMany(mappedBy="mensagem")
	private List<Avaliacao> avaliacoes;

	//bi-directional many-to-one association to Cliente
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Cliente.class, cascade={CascadeType.ALL})
	@JoinColumn(name="cod_cli")
	private Cliente cliente = new Cliente();

	//bi-directional many-to-one association to MensagemOriginal
	@OneToMany(mappedBy="mensagem")
	private List<MensagemOriginal> mensagensOriginais;

	//bi-directional many-to-one association to Resposta
	@OneToMany(mappedBy="mensagem")
	private List<Resposta> respostas;

	public Mensagem() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoAssunto getTipoAssunto() {
		return tipoAssunto;
	}

	public void setTipoAssunto(TipoAssunto tipoAssunto) {
		this.tipoAssunto = tipoAssunto;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Avaliacao addAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().add(avaliacao);
		avaliacao.setMensagem(this);

		return avaliacao;
	}

	public Avaliacao removeAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().remove(avaliacao);
		avaliacao.setMensagem(null);

		return avaliacao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<MensagemOriginal> getMensagensOriginais() {
		return this.mensagensOriginais;
	}

	public void setMensagensOriginais(List<MensagemOriginal> mensagensOriginais) {
		this.mensagensOriginais = mensagensOriginais;
	}

	public MensagemOriginal addMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagensOriginais().add(mensagemOriginal);
		mensagemOriginal.setMensagem(this);

		return mensagemOriginal;
	}

	public MensagemOriginal removeMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagensOriginais().remove(mensagemOriginal);
		mensagemOriginal.setMensagem(null);

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
		resposta.setMensagem(this);

		return resposta;
	}

	public Resposta removeResposta(Resposta resposta) {
		getRespostas().remove(resposta);
		resposta.setMensagem(null);

		return resposta;
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
		Mensagem other = (Mensagem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}