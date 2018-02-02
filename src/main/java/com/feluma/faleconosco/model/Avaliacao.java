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
	@Enumerated(EnumType.STRING)
	private StatusAvaliacao statusAvaliacao;

	//bi-directional many-to-one association to Mensagem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_men")
	private Mensagem mensagem;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_usu")
	private Usuario usuario;
	
	//bi-directional many-to-one association to UnidadeSetor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cod_set", referencedColumnName="cod_set"),
		@JoinColumn(name="cod_uni", referencedColumnName="cod_uni")
		})
	private UnidadeSetor unidadeSetor;
	
	@Column(name="flg_ati")
	private int flagAtivo;

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

	public StatusAvaliacao getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public Mensagem getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UnidadeSetor getUnidadeSetor() {
		return unidadeSetor;
	}

	public void setUnidadeSetor(UnidadeSetor unidadeSetor) {
		this.unidadeSetor = unidadeSetor;
	}
	
	public int getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(int flagAtivo) {
		this.flagAtivo = flagAtivo;
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