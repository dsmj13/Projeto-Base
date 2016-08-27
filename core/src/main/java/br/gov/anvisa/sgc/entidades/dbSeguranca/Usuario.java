package br.gov.anvisa.sgc.entidades.dbSeguranca;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.anvisa.base.comum.entidade.EntidadeBasica;


/**
 * The persistent class for the TB_USUARIO database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: Usuario
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
@Table(name="TB_USUARIO", schema="DBSEGURANCA")
public class Usuario extends EntidadeBasica{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CO_USERNAME")
	private String id;
	
	@Column(name="CO_USERNAME", updatable= false, insertable = false)
	private String userName;

	@Column(name="DS_CARGO")
	private String cargo;

	@Column(name="DS_EMAIL")
	private String email;

	@Column(name="DS_SENHA")
	private String senha;

//	@Column(name="DS_SENHA_MD5")
//	private String drmhaMD5;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_BLOQUEIO")
	private Date dtBloqueio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CADASTRO")
	private Date dtCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_VALIDADE_SENHA")
	private Date dtValidadeSenha;

	@Column(name="NO_USUARIO")
	private String nome;

	@Column(name="NU_CPF")
	private String cpf;
	
	@Column(name="NU_MATRICULA_SIAPE")
	private BigDecimal matriculaSiap;

	@Column(name="NU_TELEFONE")
	private String telefone;

	@Column(name="TP_USUARIO")
	private String tpUsuario;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
	private List<PerfilUsuario> perfisUsuarios;


	public Usuario() {
	}

	@Override
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


//	public String getDrmhaMD5() {
//		return drmhaMD5;
//	}
//
//
//	public void setDrmhaMD5(String drmhaMD5) {
//		this.drmhaMD5 = drmhaMD5;
//	}


	public Date getDtBloqueio() {
		return dtBloqueio;
	}


	public void setDtBloqueio(Date dtBloqueio) {
		this.dtBloqueio = dtBloqueio;
	}


	public Date getDtCadastro() {
		return dtCadastro;
	}


	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	public Date getDtValidadeSenha() {
		return dtValidadeSenha;
	}


	public void setDtValidadeSenha(Date dtValidadeSenha) {
		this.dtValidadeSenha = dtValidadeSenha;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public BigDecimal getMatriculaSiap() {
		return matriculaSiap;
	}


	public void setMatriculaSiap(BigDecimal matriculaSiap) {
		this.matriculaSiap = matriculaSiap;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getTpUsuario() {
		return tpUsuario;
	}


	public void setTpUsuario(String tpUsuario) {
		this.tpUsuario = tpUsuario;
	}


	public List<PerfilUsuario> getPerfisUsuarios() {
		return perfisUsuarios;
	}


	public void setPerfisUsuarios(List<PerfilUsuario> perfisUsuarios) {
		this.perfisUsuarios = perfisUsuarios;
	}
}