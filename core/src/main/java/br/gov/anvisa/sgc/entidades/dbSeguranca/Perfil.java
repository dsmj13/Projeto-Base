package br.gov.anvisa.sgc.entidades.dbSeguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;


/**
 * The persistent class for the TB_PERFIL database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: Perfil
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
@Table(name="TB_PERFIL", schema="DBSEGURANCA")
public class Perfil extends EntidadeBasica {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_PERFIL")
	private String id;

	@Column(name="DS_PERFIL")
	private String descricao;

	@Column(name="NO_PERFIL")
	private String nome;

	@Column(name="ST_ATIVO")
	private String stAtivo;
	
	@ManyToOne
	@JoinColumn(name="CO_SISTEMA")
	private Sistema sistema;

	public Perfil() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStAtivo() {
		return stAtivo;
	}

	public void setStAtivo(String stAtivo) {
		this.stAtivo = stAtivo;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	

}