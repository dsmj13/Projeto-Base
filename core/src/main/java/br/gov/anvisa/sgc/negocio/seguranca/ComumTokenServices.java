package br.gov.anvisa.sgc.negocio.seguranca;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;


/**
 * The Class ComumTokenServices.
 */
public class ComumTokenServices extends DefaultTokenServices {
	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
		OAuth2AccessToken token = super.createAccessToken(authentication);
		
		return token;
	}
}
