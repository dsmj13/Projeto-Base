package br.gov.anvisa.sgc.dominio;

import br.gov.anvisa.base.comum.i18n.ComumI18N;


/*
 * 
 * Projeto: projeto RH
 * Arquivo: MensagemEnum
 * 
 * Copyright @ Anvisa.
 *
 * Este software é confidencial e de propriedade da Anvisa.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */
/**
 * Domínio utilizado para mapear as chaves das mensagens contidas nos arquivos de propriedades.
 * @author mario.melo
 *
 */
public enum MensagemEnum implements ComumI18N {
	
	M001,
	M002,
	M003,
	M004,
	M005,
	M006,
	M007,
	
	
	MSG03,
	MSG004,
	MSG006,
	MSG013,
		
	/** The msg erro desconhecido. */
	MSG_ERRO_DESCONHECIDO("erro.desconhecido"), 
	
	USUARIO_NAO_ALTORIZADO("usuario.nao.altorizado"),
	
	
	ERRO_EXLUIR_CURSO_INSTITUICAO("erroExluirCursoInstituicao"),
	
	NULL;
	

	/** The valor. */
	private String valor;

	/**
	 * Instantiates a new mensagem enum.
	 *
	 * @param valor the valor
	 */
	private MensagemEnum(String valor) {
		this.valor = valor;
	}
	
	/**
	 * Instantiates a new mensagem enum.
	 */
	private MensagemEnum() {
	}

	/**
	 * Valor.
	 *
	 * @return the string
	 */
	public String valor() {
		return this.valor;
	}

	/**
	 * Value of arg.
	 *
	 * @param message the message
	 * @return the mensagem enum
	 */
	public static MensagemEnum valueOfArg(String message) {
		for (MensagemEnum msg : values()) {
			if (msg.valor().equals(message)) {
				return msg;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.dwlti.base.comum.i18n.ComumI18N#chave()
	 */
	@Override
	public String chave() {
		if(this.valor != null){
			return this.valor;
		}else{
			return this.name();
		}
	}

}
