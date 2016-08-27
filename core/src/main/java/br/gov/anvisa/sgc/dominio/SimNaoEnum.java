package br.gov.anvisa.sgc.dominio;

import java.io.Serializable;

import br.gov.anvisa.sgc.dominio.converter.ComumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*
 * 
 * Projeto: projeto RH
 * Arquivo: SimNaoEnum
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */
/**
 * Represemta as opcoes de sim e nao para respostas.
 *  @author mario.melo
 *
 */
@JsonSerialize(using=ComumSerializer.class)
public enum SimNaoEnum implements Serializable {
	
	/** The s. */
	S("S", "Sim"),
	
	/** The n. */
	N("N", "Não");
	
	/** The valor. */
	private final String valor;
	
	/** The label key. */
	private final String labelKey;

	/**
	 * Construtor.
	 *
	 * @param valor the valor
	 * @param label the label
	 */
	private SimNaoEnum(String valor, String label) {
		this.valor = valor;
		this.labelKey = label;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Gets the label key.
	 *
	 * @return the label key
	 */
	public String getLabelKey() {
		return labelKey;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString(){
		return valor;
	}

}