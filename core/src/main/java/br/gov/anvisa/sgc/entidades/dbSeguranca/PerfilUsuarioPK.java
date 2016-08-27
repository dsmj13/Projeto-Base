package br.gov.anvisa.sgc.entidades.dbSeguranca;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RL_PERFIL_USUARIO database table.
 * 
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: PerfilUsuarioPK
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
@Embeddable
public class PerfilUsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CO_USERNAME", insertable=false, updatable=false)
	private String username;

	@Column(name="CO_PERFIL", insertable=false, updatable=false)
	private String perfil;

	public PerfilUsuarioPK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPerfil() {
		return this.perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PerfilUsuarioPK)) {
			return false;
		}
		PerfilUsuarioPK castOther = (PerfilUsuarioPK)other;
		return 
			this.username.equals(castOther.username)
			&& this.perfil.equals(castOther.perfil);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.perfil.hashCode();
		
		return hash;
	}
}