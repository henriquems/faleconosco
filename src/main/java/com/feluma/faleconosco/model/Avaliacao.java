package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the avaliacao database table.
 * 
 */
@Entity
@Table(name="avaliacao")
@NamedQuery(name="Avaliacao.findAll", query="SELECT a FROM Avaliacao a")
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ava")
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_ava")
	private Date data;

	@Column(name="sta_ava")
	private String statusAvaliacao;

	//bi-directional many-to-one association to Mensagem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_men")
	private Mensagem mensagem;

	//bi-directional many-to-one association to UnidadeSetor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_uni_set")
	private UnidadeSetor unidadeSetor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_usu")
	private Usuario usuario;

	public Avaliacao() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatusAvaliacao() {
		return this.statusAvaliacao;
	}

	public void setStatusAvaliacao(String statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public Mensagem getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public UnidadeSetor getUnidadeSetor() {
		return this.unidadeSetor;
	}

	public void setUnidadeSetor(UnidadeSetor unidadeSetor) {
		this.unidadeSetor = unidadeSetor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Avaliacao other = (Avaliacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}