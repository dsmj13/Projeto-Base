package br.gov.anvisa.sgc.dominio.converter;

import java.io.IOException;

import br.gov.anvisa.base.comum.exception.ComumException;
import br.gov.anvisa.sgc.negocio.validacao.AssertUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;




/*
 * 
 * Projeto: projeto RH
 * Arquivo: ComumDeserializer
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */

/**
 * Classe que faz a deserializacao de enum.
 * 
 * @author mario.melo
 *
 */
public class ComumDeserializer extends JsonDeserializer<Object>{
	
	/** The Constant NAME_ENUM. */
	private static final String NAME_ENUM = "_id";
	
	/** The Constant CLAZZ. */
	private static final String CLAZZ = "clazz";
	
	/** The Constant CLAZZ. */
	private static final String MSG_ENUM_NAO_ENCONTRADO = "Enum não encontrado : ";
	
	
	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(
	 * com.fasterxml.jackson.core.JsonParser, 
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
		JsonNode node = jp.getCodec().readTree(jp);
		
		if(AssertUtil.isNull(node.get(NAME_ENUM))
				|| AssertUtil.isNull(node.get(CLAZZ))){
			return null;
		}
		
		String _id = node.get(NAME_ENUM).asText();
		String _clazz = node.get(CLAZZ).asText(); 
		
		try {
			Class clazzEnum = Class.forName(_clazz);
			return  Enum.valueOf(clazzEnum, _id);
		} catch (ClassNotFoundException e) {
			throw new ComumException(MSG_ENUM_NAO_ENCONTRADO+ _clazz, e);
		} catch(IllegalArgumentException e){
			throw new ComumException(MSG_ENUM_NAO_ENCONTRADO+ _clazz, e);
		}
	}
}
