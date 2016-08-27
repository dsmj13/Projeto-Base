package br.gov.anvisa.sgc.converter;

import java.io.Serializable;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;




/**
 * 
 * Projeto: projeto RH
 * Arquivo: ComumMapper
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 * 
 * Classe com os métodos de configuração dos conversores.
 */
/**
 * @author mario.melo
 *
 */
@Component
public class ComumMapper implements Serializable{

	private static final long serialVersionUID = 1L;

	/** The Constant PARA. */
	private static final String PARA = " para: ";
	
	/** The Constant CONVERTENDO_DE. */
	private static final String CONVERTENDO_DE = "Convertendo de: ";
	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ComumMapper.class);
	
	/**
	 * Converter.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D> D converter(Object source, Class<D> target){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getName());
		}
		
		ModelMapper map = new ModelMapper();
		//map.getConfiguration().setVerifyLazyLoadingHibernate(false);
		D retorno = map.map(source, target);
		
		return retorno;
	}
	
	/**
	 * Converter strict.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D> D converterStrict(Object source, Class<D> target){
		
		if (source == null){
			return null;
		}

		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getName());
		}
		
		ModelMapper map = new ModelMapper();
		map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//map.getConfiguration().setVerifyLazyLoadingHibernate(false);
		D retorno = map.map(source, target);
		
		return retorno;
	}
	
	/**
	 * Converter strict.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D> D converterStrict(Object source, Type target){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getClass());
		}
		
		ModelMapper map = new ModelMapper();
		map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//map.getConfiguration().setVerifyLazyLoadingHibernate(false);
		D retorno = map.map(source, target);
		
		return retorno;
	}
	
	/**
	 * Converter lazy loading.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D> D converterLazyLoading(Object source, Class<D> target){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getName());
		}
		
		ModelMapper map = new ModelMapper();
		//map.getConfiguration().setVerifyLazyLoadingHibernate(true);
		D retorno = map.map(source, target);
		
		return retorno;
	}
	
	/**
	 * Converter strict e lazy loading.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return the converted object of class D
	 */
	public <D> D converterStrictLazyLoading(Object source, Class<D> target){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getName());
		}
		
		ModelMapper map = new ModelMapper();
		map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//map.getConfiguration().setVerifyLazyLoadingHibernate(true);
		D retorno = map.map(source, target);
		
		return retorno;
	}

	/**
	 * Converter strict e lazy loading.
	 *
	 * @param <D> the generic type
	 * @param source the source
	 * @param target the target
	 * @return retorna o objeto convertido
	 */
	public <D> D converterListaStrictLazyLoading(Object source, Type target) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(CONVERTENDO_DE + source.getClass() + PARA + target.getClass());
		}
		
		ModelMapper map = new ModelMapper();
		map.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//map.getConfiguration().setVerifyLazyLoadingHibernate(true);
		D retorno = map.map(source, target);
		
		return retorno;
	}
}
