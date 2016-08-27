package br.gov.anvisa.sgc.util;

import java.util.Date;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.gov.anvisa.sgc.negocio.validacao.AssertUtil;




/**
 * Classe utilitaria para Querys.
 *
 * @author andre.minella
 * Criado em: 12/09/2014
 */
@Component
public class QueryUtil {
	
	/** The Constant PERCENT_LIKE. */
	public static final String PERCENT_LIKE = "%";

	/**
	 * Setar parametro.
	 *
	 * @param qry the qry
	 * @param chaveFiltro the chave filtro
	 * @param valorFiltro the valor filtro
	 */
	public void setarParametro(Query qry, String chaveFiltro, Object valorFiltro){
		if(AssertUtil.isEmptyOrNull(valorFiltro)){
			return;
		}
		
		qry.setParameter(chaveFiltro, valorFiltro);
	}
	
	/**
	 * Setar parametro date.
	 *
	 * @param qry the qry
	 * @param chaveFiltro the chave filtro
	 * @param valoreFiltro the valore filtro
	 */
	public void setarParametroDate(Query qry, Object[] chaveFiltro, Date... valoreFiltro){
		for (Date valor : valoreFiltro) {
			if(AssertUtil.isEmptyOrNull(valor) || !DataUtil.isDataValida(valor)){
				return;
			}
		}

		for (int i = 0; i < chaveFiltro.length; i++) {
			qry.setParameter(chaveFiltro[i].toString(), DataUtil.getDataFormatada(valoreFiltro[i]));

		}
	}
	
	/**
	 * Setar parametro like.
	 *
	 * @param qry the qry
	 * @param chaveFiltro the chave filtro
	 * @param valorFiltro the valor filtro
	 */
	public void setarParametroLike(Query qry, String chaveFiltro, Object valorFiltro){
		if(AssertUtil.isEmptyOrNull(valorFiltro)){
			return;
		}
		
		setarParametro(qry, chaveFiltro, PERCENT_LIKE + valorFiltro +PERCENT_LIKE);
	}
	
	/**
	 * Adicionar condicao.
	 *
	 * @param where the where
	 * @param condicao the condicao
	 * @param valoresFiltro the valores filtro
	 * @param chavesFiltro the chaves filtro
	 */
	public void adicionarCondicao(StringBuilder where, String condicao, Object[] valoresFiltro, Object... chavesFiltro){
		for (Object valor : valoresFiltro) {
			if(AssertUtil.isEmptyOrNull(valor)){
				return;
			}			
		}
		
		String condicaoEChave = String.format(condicao, chavesFiltro);
		
		where.append(condicaoEChave);		
	}
	
	/**
	 * Adicionar condicao.
	 *
	 * @param where the where
	 * @param condicao the condicao
	 * @param valorFiltro the valor filtro
	 * @param chaveFiltro the chave filtro
	 */
	public void adicionarCondicao(StringBuilder where, String condicao, Object valorFiltro, String chaveFiltro){
		if(AssertUtil.isEmptyOrNull(valorFiltro)){
			return;
		}		
		
		String condicaoEChave = String.format(condicao, chaveFiltro);
		
		where.append(condicaoEChave);
	}	

	/**
	 * Monta uma clausula ORDER BY de acordo com os parâmetros passados .
	 *
	 * @param orders são as colunas padrão HQL que serão ordenadas
	 * @param sorting contém a chave e o tipo de ordenação (asc ou desc)
	 * @param orderPadrao coluna HQL e tipo de ordenação (ex: entidade.coluna asc)
	 * @return clausula ORDER BY montada.
	 */
	public String obterOrderBy(Map<String, String> orders, Map<String, String> sorting, String orderPadrao) {
		Boolean isSortingVazio = sorting == null	|| sorting.isEmpty(); 
		String retorno = StringUtils.EMPTY;
		if(isSortingVazio){
			retorno = adicionarOrderByPadrao(orderPadrao);
		}else{
			retorno = adicionarOrderBy(orders, sorting); 
		}
		return retorno;
	}

	/**
	 * Adicionar order by padrao.
	 *
	 * @param order the order
	 * @return the string
	 */
	public String adicionarOrderByPadrao(String order) {
		StringBuilder orderBy = new StringBuilder("");
		String clausula = String.format(" ORDER BY %s", order);
		
		orderBy.append(clausula);
		
		return orderBy.toString();
	}
	
	/**
	 * Adicionar order by.
	 *
	 * @param orders the orders
	 * @param sorting the sorting
	 * @return the string
	 */
	private String adicionarOrderBy(Map<String, String> orders, Map<String, String> sorting) {
		StringBuilder orderBy = new StringBuilder("");
		String chave = sorting.keySet().iterator().next();
		String order = orders.get(chave);
		String sort = sorting.get(chave);
	
		String clausula = String.format(" ORDER BY %s %s", order, sort);
		
		orderBy.append(clausula);
		
		return orderBy.toString();
	}	
}
