/*
 * Projeto: pesquisa-core
 * Arquivo: SpoBaseTest.java
 * 
 * Copyright @ Minist�rio da Sa�de.
 *
 * Este software � confidencial e de propriedade do Minist�rio da Sa�de.
 * N�o � permitida sua distribui��o ou divulga��o do seu conte�do sem 
 * expressa autoriza��o do mesmo.
 */
package br.gov.anvisa.sgc.webapps.testes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.gov.anvisa.sgc.entidades.dbSeguranca.Usuario;
import br.gov.anvisa.sgc.test.comum.ExecucaoScriptListener;
import br.gov.anvisa.sgc.test.comum.TestResourceSQL;
import br.gov.anvisa.sgc.test.comum.TestUtil;


/**
 * Classe que serve de base para execucao dos testes unitarios
 *
 * @author mario.melo 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/test-service-context.xml" })
@WebAppConfiguration
@TestExecutionListeners({ExecucaoScriptListener.class})
public class TestBase {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(TestBase.class);
	
	/** The Constant ID_USUARIO. */
	private static final String ID_USUARIO = "mario.melo";

	/** The mock mvc. */
	private MockMvc mockMvc;
	
	/** The wac. */
	@Autowired
	private WebApplicationContext wac;
	
	/** The exec script. */
	@Autowired
	private ExecucaoScriptListener execScript;

//	/** The mail service. */
//	@Autowired
//	private MailService mailService;
	
	/** The is exec dml. */
	protected static boolean isExecDml;
	
	/**
	 * Inicia.
	 */
	@Before
	public void init(){
		LOGGER.info("\n\nINIT: " + isExecDml + "\n");
		MockitoAnnotations.initMocks(this);
		setMockMvc(MockMvcBuilders.webAppContextSetup(wac).build());
		
		autenticar(new ArrayList<GrantedAuthority>());
		
		if(!isExecDml){
			LOGGER.info("\nExecutando Scripts\n");
			
			for (TestResourceSQL resource : TestResourceSQL.getResourcesPorOrdemExecucao()){
				executarScript(resource);
			}
			
			isExecDml = true;
		}
		
		// Faz um mock do envio de email
		//ReflectionTestUtils.setField(mailService, "mailSender", Mockito.mock(JavaMailSender.class));
	}
	
	/**
	 * Base test.
	 */
	@Test
	public void baseTest(){
		LOGGER.info("Executando testes da classe unitária: " + getClass().getName());
	}
	
	/**
	 * Executa um script definido no {@link TestResourceSQL}.
	 *
	 * @param resource resorce
	 */
	protected void executarScript(TestResourceSQL resource){
		execScript.initDML(wac, resource);
	}
	
	/**
	 * Retorna uma lista com as Authorities de teste unitário.
	 *
	 * @return {@link List} de {@link GrantedAuthority}
	 */
	protected List<GrantedAuthority> getAutorizacoesTeste() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		autorizacoes.add(new SimpleGrantedAuthority( TestUtil.ROLE ));
		return autorizacoes;
	}
	
	/**
	 * Autentica o usuário com a lista de roles informada.
	 *
	 * @param usuario usuario
	 * @param roles regras
	 */
	protected void autenticar(Usuario usuario, List<GrantedAuthority> roles) {
		OAuth2Authentication auth = new OAuth2Authentication(null, TestUtil.criarToken(usuario, roles));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	/**
	 * Autentica um mock de usuário com as roles informadas.
	 *
	 * @param roles regras
	 */
	protected void autenticar(List<GrantedAuthority> roles) {
		autenticar(obterUsuarioMock(), roles);
	}
	
	/**
	 * Autentica um mock de usuário com as roles de teste.
	 */
	protected void autenticar() {
		autenticar( getAutorizacoesTeste() );
	}
	
	/**
	 * Cria um mock de um usuário do sistema para ser utilizado nos testes.
	 *
	 * @return Usuario Mockado
	 */
	protected Usuario obterUsuarioMock(){
		Usuario usuario = new Usuario();
		usuario.setEmail("mario.melo@teste.com");
		usuario.setCpf("01201396425");
		usuario.setId(ID_USUARIO);
		usuario.setNome("Mario Melo");
		
		return usuario;
	}

	/**
	 * Gets the mock mvc.
	 *
	 * @return the mock mvc
	 */
	public MockMvc getMockMvc() {
		return mockMvc;
	}

	/**
	 * Sets the mock mvc.
	 *
	 * @param mockMvc the new mock mvc
	 */
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	
	/**
	 * Gets the resource.
	 *
	 * @param path the path
	 * @return the resource
	 */
	public Resource getResource(String path) {
		return wac.getResource(path);
	}
	
	/**
	 * Gets the resource as file.
	 *
	 * @param path the path
	 * @return the resource as file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File getResourceAsFile(String path) throws IOException {
		return getResource(path).getFile();
	}
}
