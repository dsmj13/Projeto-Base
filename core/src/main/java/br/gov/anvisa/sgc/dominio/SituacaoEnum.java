package br.gov.anvisa.sgc.dominio;

import java.io.Serializable;

/*
 * 
 * Projeto: projeto RH
 * Arquivo: SituacaoEnum
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 *
 */
/**
 * Enum boleano do que representa as opcoes do banco
 * @author mario.melo
 *
 */
//@JsonSerialize(using=EnumConverter.class)
public enum SituacaoEnum implements Serializable {
	
	
	N("N", "label.inativo"),
	/** The s. */
	S("S", "label.ativo");
	
	/** The n. */
	
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
	private SituacaoEnum(String valor, String label) {
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
	
	/**
	 * To enum.
	 *
	 * @param flag the flag
	 * @return the situacao enum
	 */
	public static SituacaoEnum toEnum(boolean flag){
		return flag ? S : N;
	}
	
	/**
	 * To boolean.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	public boolean toBoolean(SituacaoEnum flag){
		return flag.equals( S );
	}
	
	/**
	 * Retorna o valor booleano que o Enum representa.
	 *
	 * @return the boolean
	 */
	public boolean getBoolean(){
		return toBoolean(this);
	}
}