package br.gov.anvisa.sgc.negocio.seguranca;

import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;


public class ComumAuthenticateException extends InvalidGrantException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant DELIM. */
	private static final String DELIM = ", ";
	
	/** The Constant ESCAPE. */
	private static final String ESCAPE = "\"";
	
	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.common.exceptions.InvalidGrantException#getOAuth2ErrorCode()
	 */
	@Override
	public String getOAuth2ErrorCode() {
		return "error_autenticacao";
	}
			
	/**
	 * Construtor com mensagem e erro.
	 *
	 * @param mensagem Mensagem ou Chave
	 * @param erro Erro ocorrido para ser mantido na pilha
	 */
	public ComumAuthenticateException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
		
	public ComumAuthenticateException(String mensagem) {
		super(mensagem);
	}
	
	
	public ComumAuthenticateException() {
		super("Erro desconhecisdo");		
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.common.exceptions.OAuth2Exception#getSummary()
	 */
	@Override
	public String getSummary(){

		StringBuilder builder = new StringBuilder();

		String delim = "";

		String error = this.getOAuth2ErrorCode();
		if (error != null) {
			builder.append(delim).append("error=\"").append(error).append(ESCAPE);
			delim = DELIM;
		}

		String errorMessage = this.getMessage();
		if (errorMessage != null) {
			builder.append(delim).append("error_description=\"").append(errorMessage).append(ESCAPE);
			delim = DELIM;
		}

		Map<String, String> additionalParams = this.getAdditionalInformation();
		if (additionalParams != null) {
			for (Map.Entry<String, String> param : additionalParams.entrySet()) {
				builder.append(delim).append(param.getKey()).append("=\"").append(param.getValue()).append(ESCAPE);
				delim = DELIM;
			}
		}
		
		return builder.toString();
	}
}
