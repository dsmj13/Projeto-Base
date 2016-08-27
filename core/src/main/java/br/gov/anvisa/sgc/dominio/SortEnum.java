package br.gov.anvisa.sgc.dominio;

import java.io.Serializable;

public enum SortEnum implements Serializable{	
	ASC(1, "Mais recente"), DESC(2, "Mais antigo");

	private Integer id;
	private String descricao;

	private SortEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
