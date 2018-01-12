package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the resposta database table.
 * 
 */
@Entity
@Table(name="resposta")
@NamedQuery(name="Resposta.findAll", query="SELECT r FROM Resposta r")
public class Resposta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_res")
	private Long codigo;
	
	@Column(name="des_men")
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_res")
	private Date data;
	
	//bi-directional many-to-one association to Mensagem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_men")
	private Mensagem mensagem;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_usu")
	private Usuario usuario;
	
	//bi-directional many-to-one association to MensagemOriginal
	@OneToMany(mappedBy="resposta")
	private List<MensagemOriginal> mensagensOriginais;

	public Resposta() {
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

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public List<MensagemOriginal> getMensagensOriginais() {
		return this.mensagensOriginais;
	}

	public void setMensagensOriginais(List<MensagemOriginal> mensagensOriginais) {
		this.mensagensOriginais = mensagensOriginais;
	}

	public MensagemOriginal addMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagensOriginais().add(mensagemOriginal);
		mensagemOriginal.setResposta(this);

		return mensagemOriginal;
	}

	public MensagemOriginal removeMensagemOriginal(MensagemOriginal mensagemOriginal) {
		getMensagensOriginais().remove(mensagemOriginal);
		mensagemOriginal.setResposta(null);

		return mensagemOriginal;
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
		Resposta other = (Resposta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}