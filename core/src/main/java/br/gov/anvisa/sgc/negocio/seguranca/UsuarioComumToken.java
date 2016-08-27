package br.gov.anvisa.sgc.negocio.seguranca;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import br.gov.anvisa.sgc.entidades.dbSeguranca.Perfil;

public class UsuarioComumToken extends	AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4116854970598324449L;

	/** The principal. */
	private final Object principal;
	
	/** The credentials. */
	private Object credentials;
	
	/** The senha. */
	private String senha;
	
	/** The cpf. */
	private String cpf;
	
	/** The nome. */
	private String nome;
	
	/** The email. */
	private String email;
	
	/** The id usuario autenticado. */
	private String idUsuarioAutenticado;
	
	private Perfil perfilSelecionado;
	
	
	/**
	 * Instantiates a new usuario comum token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 */
	public UsuarioComumToken(Object principal,
			Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
	
	/**
	 * Instantiates a new usuario comum token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 * @param authorities the authorities
	 */
	public UsuarioComumToken(Object principal,
			Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AbstractAuthenticationToken#setAuthenticated(boolean)
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated)throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException("Use o construtor que recebe uma lista de GrantedAuthority");
		}

		super.setAuthenticated(false);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AbstractAuthenticationToken#eraseCredentials()
	 */
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the id usuario autenticado.
	 *
	 * @return the idUsuarioAutenticado
	 */
	public String getIdUsuarioAutenticado() {
		return idUsuarioAutenticado;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}


	/**
	 * Sets the cpf.
	 *
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the id usuario autenticado.
	 *
	 * @param idUsuarioAutenticado the idUsuarioAutenticado to set
	 */
	public void setIdUsuarioAutenticado(String idUsuarioAutenticado) {
		this.idUsuarioAutenticado = idUsuarioAutenticado;
	}

	/**
	 * Gets the senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the senha.
	 *
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}
	
	

}
