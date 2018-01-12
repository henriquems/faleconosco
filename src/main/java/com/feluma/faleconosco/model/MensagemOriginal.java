package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mensagem_original database table.
 * 
 */
@Entity
@Table(name="mensagem_original")
@NamedQuery(name="MensagemOriginal.findAll", query="SELECT m FROM MensagemOriginal m")
public class MensagemOriginal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_men_ori")
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_men_ori")
	private Date data;

	@Column(name="des_men_ori")
	private String descricao;

	//bi-directional many-to-one association to Mensagem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_men")
	private Mensagem mensagem;
	
	//bi-directional many-to-one association to Resposta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_res")
	private Resposta resposta;	

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_usu")
	private Usuario usuario;

	public MensagemOriginal() {
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Mensagem getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Resposta getResposta() {
		return this.resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
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
		MensagemOriginal other = (MensagemOriginal) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}