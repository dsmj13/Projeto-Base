package br.gov.anvisa.sgc.entidades.dbsistru;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;


/**
 * The persistent class for the TB_UNIDADE_ORG database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: UnidadeOrg
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
@Table(name="TB_UNIDADE_ORG", schema="DBSISTRU")
public class UnidadeOrg extends EntidadeBasica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_SEQ_INTERNO_UORG")
	private Long id;
	
	@Column(name="NO_UNIDADE_ORG")
	private String nome;
	
	@Column(name="SG_UNIDADE_ORG")
	private String sigla;
	
	@Column(name="CO_UF")
	private String uf;
	
	public UnidadeOrg() {
	}
	
	public UnidadeOrg(Long id, String nome, String sigla, String uf) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.uf = uf;
	}



	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}