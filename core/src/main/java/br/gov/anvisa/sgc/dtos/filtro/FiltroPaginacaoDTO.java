package br.gov.anvisa.sgc.dtos.filtro;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


/*
 * 
 * Projeto: projeto RH
 * Arquivo: FiltroPaginacaoDTO
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 * 
 */
/**
 * @author mario.melo
 *Classe para ser extendida pelos filtros de pesquisa que contém paginação e ordenação.
 */
public class FiltroPaginacaoDTO {
	
	/** The sorting. */
	private Map<String, String> sorting;
	
	/** The count. */
	private Integer count;
	
	/** The page. */
	private Integer page;
	
	/**
	 * Instantiates a new filtro paginacao dto.
	 */
	public FiltroPaginacaoDTO() {
	}
	
	/**
	 * Gets the sorting.
	 *
	 * @return the sorting
	 */
	public Map<String, String> getSorting() {
		return sorting;
	}
	
	/**
	 * Seta sorting.
	 *
	 * @param sorting the sorting
	 */
	public void setSorting(Map<String, String> sorting) {
		this.sorting = sorting;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	
	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	/**
	 * Constrói uma instância do {@link Pageable} de acordo com os parâmetros passados na URL
	 *
	 * @return the pageable
	 */
	public Pageable makePageable(){
		return new PageRequest(page - 1, count);
	}
}
