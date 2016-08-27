package br.gov.anvisa.sgc.dtos;

import java.io.Serializable;

/*
 * 
 * Projeto: projeto RH
 * Arquivo: ComboDTO
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */
/**
 * DTO representa as opcoes de selec/combo
 * @author mario.melo
 *
 */
public class ComboDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String descricao;
	
	
	
	public ComboDTO(Long id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	
	public ComboDTO(){}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}

