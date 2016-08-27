package br.gov.anvisa.sgc.apresentacao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.anvisa.base.comum.exception.ComumNegocioException;
import br.gov.anvisa.base.web.mensagem.Resposta;
import br.gov.anvisa.sgc.converter.ComumMapper;
import br.gov.anvisa.sgc.dtos.UsuarioDTO;
import br.gov.anvisa.sgc.negocio.seguranca.ComumProvider;
import br.gov.anvisa.sgc.negocio.seguranca.UsuarioComumToken;
import br.gov.anvisa.sgc.negocio.service.TextFileIndexer;

/*
 * 
 * Projeto: projeto RH
 * Arquivo: LoginController
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

@Controller
@RequestMapping("/api/public/login")
public class LoginController {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);
	
	/** The token store. */
	@Autowired
	private InMemoryTokenStore tokenStore;
	
	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ComumProvider comumProvider;
	
	@Autowired
	private ComumMapper mapper;
	
	/**
	 * Obtem os dados do usuário logado.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/dadosUsuarioLogado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resposta> obterDadosUsuarioLogado(){
		LOGGER.info("obtendo informações do usuário autenticado");
		
		OAuth2Authentication auth = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioComumToken user = (UsuarioComumToken)auth.getUserAuthentication();
		
		Resposta resp = new Resposta.Builder()
			.resultado(converterUsuario(user))
			.build();

		return new ResponseEntity<Resposta>(resp,  HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resposta> teste() throws ComumNegocioException, IOException{
		LOGGER.info("obtendo informações do usuário autenticado");
		
		TextFileIndexer txt = null;
		
		try{
		 txt = new TextFileIndexer("/home/mario/Desenv/index/XXX");
		}catch(Exception e){
			LOGGER.info(e.getStackTrace());
		}
		
		txt.testeIndexDocumento();
		txt.closeIndex();
		return null;
	}
	
	
	/**
	 * realiza o Logout de um usuario.
	 *
	 * @param value the value
	 * @return the response entity
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resposta> logout(@RequestParam("token") String value){
		
		LOGGER.info("Logout do usuário");
		SecurityContextHolder.clearContext();
		tokenStore.removeAccessToken(value);
		
		Resposta resp = new Resposta.Builder()
			.build();

		return new ResponseEntity<Resposta>(resp,  HttpStatus.OK);
	}

	/**
	 * Converter usuario.
	 *
	 * @param user the user
	 * @return the usuario dto
	 */
	private UsuarioDTO converterUsuario(UsuarioComumToken user) {
		
		UsuarioDTO dto = new UsuarioDTO();
		
		UsuarioDTO.Usuario usuario = new UsuarioDTO().new Usuario();
		usuario.setId(user.getIdUsuarioAutenticado());
		usuario.setNome(user.getNome());
		usuario.setCpf(user.getCpf());
		usuario.setEmail(user.getEmail());
		usuario.setRoles(converterRoles(user.getAuthorities()));
		
		dto.setUsuario(usuario);
		return dto;
	}
	
	/**
	 * Converter roles.
	 *
	 * @param authorities the authorities
	 * @return the list
	 */
	private List<String> converterRoles(Collection<GrantedAuthority> authorities) {
		
		List<String> retorno = new ArrayList<String>();
		if(authorities != null){
			for (GrantedAuthority grantedAuthority : authorities) {
				retorno.add(grantedAuthority.getAuthority());
			}
		}
		return retorno;
	}
	
}