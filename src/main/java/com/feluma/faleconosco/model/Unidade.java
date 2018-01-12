package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidade database table.
 * 
 */
@Entity
@Table(name="unidade")
@NamedQuery(name="Unidade.findAll", query="SELECT u FROM Unidade u")
public class Unidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_uni")
	private Long codigo;

	@Column(name="des_uni")
	private String descricao;

	//bi-directional many-to-one association to UnidadeSetor
	@OneToMany(mappedBy="unidade")
	private List<UnidadeSetor> unidadeSetores;

	public Unidade() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<UnidadeSetor> getUnidadeSetores() {
		return this.unidadeSetores;
	}

	public void setUnidadeSetors(List<UnidadeSetor> unidadeSetores) {
		this.unidadeSetores = unidadeSetores;
	}

	public UnidadeSetor addUnidadeSetor(UnidadeSetor unidadeSetor) {
		getUnidadeSetores().add(unidadeSetor);
		unidadeSetor.setUnidade(this);

		return unidadeSetor;
	}

	public UnidadeSetor removeUnidadeSetor(UnidadeSetor unidadeSetor) {
		getUnidadeSetores().remove(unidadeSetor);
		unidadeSetor.setUnidade(null);

		return unidadeSetor;
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
		Unidade other = (Unidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}