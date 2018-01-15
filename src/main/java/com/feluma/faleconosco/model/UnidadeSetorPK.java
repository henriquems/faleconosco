package com.feluma.faleconosco.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the unidade_setor database table.
 * 
 */
@Embeddable
public class UnidadeSetorPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "cod_uni")
	private Unidade unidade = new Unidade();

	@ManyToOne
	@JoinColumn(name = "cod_set")
	private Setor setor = new Setor();

	public UnidadeSetorPK() {
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		UnidadeSetorPK other = (UnidadeSetorPK) obj;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

}