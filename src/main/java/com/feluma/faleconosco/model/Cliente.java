package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_cli")
	private Long codigo;

	@NotEmpty
	@Column(name="nom_cli")
	private String nome;
	
	@NotEmpty
	@Email
	@Column(name="ema_cli")
	private String email;
	
	@Column(name="tel_cli")
	private String telefone;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_cli")
	private Date data;
	
	//bi-directional many-to-one association to Mensagem
	@OneToMany(mappedBy="cliente")
	private List<Mensagem> mensagens;

	public Cliente() {
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

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Mensagem> getMensagens() {
		return this.mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem addMensagem(Mensagem mensagem) {
		getMensagens().add(mensagem);
		mensagem.setCliente(this);

		return mensagem;
	}

	public Mensagem removeMensagem(Mensagem mensagem) {
		getMensagens().remove(mensagem);
		mensagem.setCliente(null);

		return mensagem;
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}