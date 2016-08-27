package br.gov.anvisa.sgc.entidades.dbSeguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;


/**
 * The persistent class for the TB_SISTEMA database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: Sistema
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
 */
@Entity
@Table(name="TB_SISTEMA", schema="DBSEGURANCA")
public class Sistema extends EntidadeBasica{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_SISTEMA")
	private String id;

	@Column(name="DS_HIPERLINK")
	private String link;

	@Column(name="NO_RESPONSAVEL")
	private String responsavel;

	@Column(name="NO_SISTEMA")
	private String nome;

	@Column(name="ST_BLOQUEADO")
	private String stBloqueado;

	@Column(name="TP_SISTEMA")
	private String tpSistema;

	public Sistema() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStBloqueado() {
		return stBloqueado;
	}

	public void setStBloqueado(String stBloqueado) {
		this.stBloqueado = stBloqueado;
	}

	public String getTpSistema() {
		return tpSistema;
	}

	public void setTpSistema(String tpSistema) {
		this.tpSistema = tpSistema;
	}

	
	
}