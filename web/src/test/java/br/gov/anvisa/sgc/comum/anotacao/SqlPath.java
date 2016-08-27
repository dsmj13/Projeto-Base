/*
* Projeto: ouvidorsus-web
* Arquivo: SqlFileLocation.java
* 
* Copyright @ Ministério da Saúde.
*
* Este software é confidencial e de propriedade do Ministério da Saúde.
* Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
* expressa autorização do mesmo.
*/
package br.gov.anvisa.sgc.comum.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.gov.anvisa.sgc.test.comum.TestResourceSQL;



/**
 * Anotação utilitária para a execução de testes.
 *
 * @author marcelo.nogueira
 * Criado em: 24/04/2014
 */
@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlPath {
	
	/**
	 * Value.
	 *
	 */
	TestResourceSQL[] value();
}
