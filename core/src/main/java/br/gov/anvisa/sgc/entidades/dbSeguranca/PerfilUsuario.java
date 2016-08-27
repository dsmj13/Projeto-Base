package br.gov.anvisa.sgc.entidades.dbSeguranca;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;


/**
 * The persistent class for the RL_PERFIL_USUARIO database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: PerfilUsuario
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
@Table(name="RL_PERFIL_USUARIO", schema="DBSEGURANCA")
public class PerfilUsuario extends EntidadeBasica  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PerfilUsuarioPK id;

	@Column(name="CO_USERNAME_GRANTOR")
	private String usernameGrantor;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CADASTRO")
	private Date dtCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_EXPIRACAO")
	private Date dtExpiracao;

	@Column(name="NO_AUTORIZADOR")
	private String noAutorizador;

	@Column(name="QT_TAREFAS")
	private BigDecimal qtTarefas;

	//bi-directional many-to-one association to TbPerfil
	@ManyToOne
	@JoinColumn(name="CO_PERFIL", insertable=false, updatable=false)
	private Perfil perfil;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne
	@JoinColumn(name="CO_USERNAME", insertable=false, updatable=false)
	private Usuario usuario;

	public PerfilUsuario() {
	}
	@Override
	public PerfilUsuarioPK getId() {
		return id;
	}

	public void setId(PerfilUsuarioPK id) {
		this.id = id;
	}

	public String getUsernameGrantor() {
		return usernameGrantor;
	}

	public void setUsernameGrantor(String usernameGrantor) {
		this.usernameGrantor = usernameGrantor;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public String getNoAutorizador() {
		return noAutorizador;
	}

	public void setNoAutorizador(String noAutorizador) {
		this.noAutorizador = noAutorizador;
	}

	public BigDecimal getQtTarefas() {
		return qtTarefas;
	}

	public void setQtTarefas(BigDecimal qtTarefas) {
		this.qtTarefas = qtTarefas;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}