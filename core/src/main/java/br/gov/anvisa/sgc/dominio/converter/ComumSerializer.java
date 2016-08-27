package br.gov.anvisa.sgc.dominio.converter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/*
 * 
 * Projeto: projeto RH
 * Arquivo: ComumSerializer
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */

/**
 * Classe que faz a serializacao de um enum
 * @author mario.melo
 *
 */
public class ComumSerializer extends JsonSerializer<Object>{
	
	/** The Constant NAME_ENUM. */
	private static final String NAME_ENUM = "_id";
	
	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ComumSerializer.class);

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, 
	 * com.fasterxml.jackson.core.JsonGenerator, 
	 * com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException	 {

		jgen.writeStartObject();
		
		try {
			
			Map<Object, Object> mapa = PropertyUtils.describe(value);
			
			for(Entry<Object, Object> map : mapa.entrySet()){
				
				if (chavesIgnoradasPeloDescribe(map)) {
					
					jgen.writeFieldName(map.getKey().toString());
					jgen.writeString( map.getValue().toString());
				}
			}
			
			jgen.writeFieldName(NAME_ENUM);
			jgen.writeString(value.toString());
			
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		jgen.writeEndObject();
	}

	/**
	 * Chaves ignoradas pelo describe.
	 *
	 * @param map the map
	 * @return true, if successful
	 */
	private boolean chavesIgnoradasPeloDescribe(Entry<Object, Object> map) {
		return !"class".equals(map.getKey())
				&& !"declaringClass".equals(map.getKey()) 
					&& !"enumMap".equals(map.getKey());
	}
}
