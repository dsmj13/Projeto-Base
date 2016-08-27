package br.gov.anvisa.sgc.dtos;

import java.io.Serializable;
import java.util.List;


/*
 * 
 * Projeto: projeto RH
 * Arquivo: UsuarioDTO
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
public class UsuarioDTO implements Serializable {
	
	/** UID. */
	private static final long serialVersionUID = -8740037604023577027L;
	
	/** The id. */
	private String id;
	
	/** The usuario. */
	private Usuario usuario;

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * The Class Usuario.
	 */
	public class Usuario implements Serializable{
	
		private static final long serialVersionUID = -7713664960972958438L;

		/** The id. */
		private String id;
		
		/** The nome. */
		private String nome;
		
		/** The email. */
		private String email;
		
		/** The cpf. */
		private String cpf;
		
		/** The roles. */
		private List<String> roles;
		

		/**
		 * Gets the nome.
		 *
		 * @return the nome
		 */
		public String getNome() {
			return nome;
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
		 * Gets the email.
		 *
		 * @return the email
		 */
		public String getEmail() {
			return email;
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
		 * Gets the roles.
		 *
		 * @return the roles
		 */
		public List<String> getRoles() {
			return roles;
		}
		
		/**
		 * Sets the roles.
		 *
		 * @param roles the roles to set
		 */
		public void setRoles(List<String> roles) {
			this.roles = roles;
		}
		
		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		
		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(String id) {
			this.id = id;
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
		 * Sets the cpf.
		 *
		 * @param cpf the cpf to set
		 */
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		
	}
	
}

