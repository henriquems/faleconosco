package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the setor database table.
 * 
 */
@Entity
@Table(name = "setor")
@NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s")
public class Setor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_set")
	private Long codigo;
	
	@NotEmpty
	@Column(name = "des_set")
	private String descricao;

	//bi-directional many-to-many association to Unidade
	@ManyToMany
	@JoinTable(
		name="unidade_setor"
		, joinColumns={
			@JoinColumn(name="cod_set")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cod_uni")
			}
		)
	private List<Unidade> unidades = new ArrayList<Unidade>();

	public Setor() {
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

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
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
		Setor other = (Setor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}