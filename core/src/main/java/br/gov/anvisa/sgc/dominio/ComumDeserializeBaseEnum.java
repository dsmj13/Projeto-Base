package br.gov.anvisa.sgc.dominio;



/*
 * 
 * Projeto: projeto RH
 * Arquivo: ComumDeserializeBaseEnum
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */
/**
 * @author mario.melo
 * 
 * Interface que deve ser implementada nos Enum que utilizam a anotação @JsonDeserialize.
 *
 *
 */
public interface ComumDeserializeBaseEnum {

	/**
	 * Método que deve ser implementado da seguinte maneira
	 * this.getClass().getCanonicalName();
	 * 
	 * @return Retorna o nome da classe Ex.: br.com.dwlti.xpto.Class
	 */
	String getClazz();
}
