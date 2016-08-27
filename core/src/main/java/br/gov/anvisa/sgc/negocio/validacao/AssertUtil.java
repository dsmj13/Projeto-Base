package br.gov.anvisa.sgc.negocio.validacao;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;



/**
 * Classe utilitaria para fazer verificações  .
 *
 * @author andre.minella
 * Criado em: 06/06/2014
 */
public final class AssertUtil{

	
	/**
	 * Instantiates a new assert util.
	 */
	private AssertUtil(){
	}
	
	/**
	 * Retorna true caso encontre um objeto nulo.
	 *
	 * @param objetos the objetos
	 * @return true, if is null
	 */
	public static boolean isNull(final Object... objetos){
		
		if (objetos == null){
			return true;
		}
			
		for(Object objeto : objetos ){
			if(objeto == null){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Retorna true caso todos os objetos estejam not null and Empty.
	 * @param objetos
	 * @return
	 */
	public final static boolean isNotNullAndNotEmpty(Object... objetos){
		return !isNull(objetos) && !isEmpty(objetos);
	}
	
	/**
	 * Retorna true caso todos os objetos estejam not null.
	 *
	 * @param objetos the objetos
	 * @return true, if is not null
	 */
	public static boolean isNotNull(final Object... objetos){
		return !isNull(objetos);
	}
	
	/**
	 * Retorna true caso encontr um objeto vazio.
	 *
	 * @param objetos the objetos
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object... objetos){
		for(Object objeto : objetos ){
			if(!notEmpty(objeto)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna true caso encontre um objeto nulo.
	 *
	 * @param objetos the objetos
	 * @return true, if is empty or null
	 */
	public static boolean isEmptyOrNull(Object... objetos){
		for(Object objeto : objetos ){
			if(objeto == null || !notEmpty(objeto)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Retorna true caso todos os objetos estejam  null.
	 *
	 * @param objetos the objetos
	 * @return true, if successful
	 */
	public static boolean allNull(Object... objetos){
		boolean sucesso = true;
		
		for(Object objeto : objetos ){
			if(objeto != null){
				sucesso = false;
				break;
			}
		}
		
		return sucesso;
	}
	
	/**
	 * Retorna true caso todos os objetos estejam not null.
	 * 
	 * @param objetos the objetos
	 * @return true, if successful
	 */
	public static boolean allNotNull(Object... objetos){
		boolean sucesso = true;
		
		for(Object objeto : objetos ){
			if(objeto == null){
				sucesso = false;
				break;
			}
		}
		
		return sucesso;
	}
	
	/**
	 * Retorna true caso encontr um objeto vazio.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	@SuppressWarnings("rawtypes")
	public static boolean notEmpty(Object value) {
		boolean retorno = true;
		if (value.getClass().isArray()) {
			retorno =  Array.getLength(value) > 0;
		}else
		if (value instanceof Collection) {
			retorno = !((Collection)value).isEmpty();
		}else
		if (value instanceof Map){
			retorno = !((Map)value).isEmpty();
		}	
		if(value instanceof String){
			retorno = ((String) value).length() > 0;
		}
		return retorno;
	}
}
