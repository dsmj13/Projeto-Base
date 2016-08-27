package br.gov.anvisa.sgc.negocio.seguranca;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.stereotype.Component;

import br.gov.anvisa.base.comum.exception.ComumException;
import br.gov.anvisa.base.comum.i18n.Comum18NUtil;
import br.gov.anvisa.sgc.converter.ComumMapper;
import br.gov.anvisa.sgc.dominio.MensagemEnum;
import br.gov.anvisa.sgc.entidades.dbSeguranca.Perfil;
import br.gov.anvisa.sgc.entidades.dbSeguranca.Usuario;
//import br.gov.anvisa.sgc.negocio.service.UsuarioService;

/**
 * Classe que gerencia a autenticao do usuario
 */

/**
 * 
 * Projeto: projeto RH
 * Arquivo: ComumProvider
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
@Component
public class ComumProvider implements AuthenticationProvider  {

	private static final String ROLE_CLIENT_OAUTH = "ROLE_CLIENT_OAUTH";

	/** The Constant serialVersionUID. */
	
	/** The Constant logger. */
	private static final Log LOGGER = LogFactory.getLog(ComumProvider.class);

	/** The saude i18n util. */
	@Autowired
	private Comum18NUtil comum18NUtil;
	
	@Autowired
	private ComumMapper mapper;
	
	/** The token store. */
	@Autowired
	private InMemoryTokenStore tokenStore;
	
	/** The request. */
	@Autowired(required=true)
	private HttpServletRequest request;
	
//	@Autowired
//	private UsuarioService usuarioService;
//	
	@Autowired
	private String codigoSistemaPom;
	
	/** The Constant COOKIE_MSTOKEN. */
	private static final String COOKIE_DWLTITOKEN = "dwltiToken";
	
	/**
	 * Retorna o nome do sistema configurado no POM.
	 *
	 * @return O nome do sistema
	 */


	/**
	 * Cria o usuário Pesquisa autenticado com sucesso.
	 *
	 * @param retorno Dados do usuário autenticado.
	 * @param senha the senha
	 * @return Retorna o token com os perfis
	 */
	private UsuarioComumToken criarUsuario(Usuario user, String senha){

		UsuarioComumToken token = null;
		Perfil perfil = null;
		
		if(user.getPerfisUsuarios().size() == 1){
			perfil = user.getPerfisUsuarios().get(0).getPerfil();
			token = criarToken(user, senha, perfil);
		}
		
		return token;
	}
	
	/**
	 *  Cria o token de autenticação. 
	 *
	 * @param user the user
	 * @param senha the senha
	 * @param perfil the perfil
	 * @return the usuario comum token
	 */

	private UsuarioComumToken criarToken(Usuario user, String senha, Perfil perfil) {

		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		autorizacoes.add(new SimpleGrantedAuthority(ROLE_CLIENT_OAUTH));
		autorizacoes.add(new SimpleGrantedAuthority(String.valueOf(new Date().getTime())));
		UsuarioComumToken token = new UsuarioComumToken(user.getId(), senha, autorizacoes);
		
		token.setEmail(user.getEmail());
		token.setCpf(user.getCpf());
		token.setNome(user.getNome());
		token.setSenha(senha);
		token.setIdUsuarioAutenticado(user.getId());
		token.setPerfilSelecionado(user.getPerfisUsuarios().get(0).getPerfil());
		
		return token;
	}

	/**
	 * recupera o usuario logado.
	 *
	 * @return the usuario logado
	 */
	public UsuarioComumToken getUsuarioLogado() {
		
		OAuth2Authentication auth = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
		UsuarioComumToken usuario = null;
		
		if ( auth != null && auth.getUserAuthentication() != null ) {
			usuario = (UsuarioComumToken) auth.getUserAuthentication();
		} else {
			usuario = getUsuarioPorCookie();
		}
		
		return usuario;
	}

	/**
	 * Retorna o usuário logado utilizando o Token que está dentro do Cookie.
	 *
	 * @return the usuario por cookie
	 */
	private UsuarioComumToken getUsuarioPorCookie() {
		UsuarioComumToken usuario = null;
		
		Cookie dwltiToken = getCookieDwltiToken();
		
		if ( dwltiToken != null && StringUtils.isNotBlank(dwltiToken.getValue()) ) {

			String tokenPostfix = StringUtils.replace(dwltiToken.getValue(), "%22", "");
			
			OAuth2Authentication authentication = tokenStore.readAuthentication(tokenPostfix);
			
			if ( authentication != null ) {
				usuario = (UsuarioComumToken) authentication.getUserAuthentication();
			}
		}
		
		return usuario;
	}


	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authetication) throws AuthenticationException {
		boolean isAutenticacao = isAutenticacao(authetication);
				
		try {
			if (isAutenticacao) {
				
				String login = authetication.getName();
				String senha = PasswordEncoderAnvisa.encode(new String(Base64.decode(((String)authetication.getCredentials()).getBytes())));
					
				Usuario usuario = new Usuario();
//				usuario = usuarioService.findUser(login, senha, codigoSistemaPom);
				
				if(usuario == null){
					throw new ComumException(comum18NUtil.getProperty(MensagemEnum.USUARIO_NAO_ALTORIZADO.valor()));
				}

				UsuarioComumToken user = criarUsuario(usuario, senha);
				user.setCredentials(new Date().getTime());
				
				return user;				
			}else{	
				UsuarioComumToken token = (UsuarioComumToken)authetication.getPrincipal();
				return token;
			}
			
		} catch (ComumException e) {
			throw new ComumAuthenticateException(e.getMessage(),   e);
		} 
	}
	
	/**
	 * Método que verifica uma chamada de autenticação ou troca de perfil. 
	 * 
	 * @param authetication {@link UsernamePasswordAuthenticationToken} token de autenticação.
	 * @return Se o {@link Principal} for uma instancia de {@link UsuarioComumToken} indica que a 
	 * requisição não é de autenticação (<code>false</code>), caso contrário, indica uma requisição de autenticação
	 * <code>true</code>.
	 *  
	 */
	private boolean isAutenticacao(Authentication authetication) {
		
		if(authetication.getPrincipal() instanceof UsuarioComumToken){
			return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	/**
	 * recupera o token de autenticacao 
	 *
	 * @return the cookie dwlti token
	 */
	private Cookie getCookieDwltiToken() {
		Cookie dwltiToken = null;
		
		if ( request.getCookies() != null ) {
			for(Cookie c : request.getCookies()){
				if ( COOKIE_DWLTITOKEN.equals( c.getName() ) ) {
					dwltiToken = c;
					break;
				}
			}
		}
		
		return dwltiToken;
	}
}