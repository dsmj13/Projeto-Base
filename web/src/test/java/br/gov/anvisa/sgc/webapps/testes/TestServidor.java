// CHECKSTYLE:OFF
package br.gov.anvisa.sgc.webapps.testes;//NOPMD

import static br.gov.anvisa.sgc.test.comum.MatchersUtil.todosOsItens;
import static br.gov.anvisa.sgc.test.comum.TestUtil.obterJsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import br.gov.anvisa.sgc.comum.anotacao.SqlPath;
import br.gov.anvisa.sgc.dominio.MensagemEnum;
import br.gov.anvisa.sgc.test.comum.TestResourceSQL;

/**
 * The Class TestParecerTecnico.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServidor extends TestBase{
	
	
	/** The Constant RESOURCE. */
	private static final String RESOURCE = "/api/servidor";
	
	/** The Constant JSONPATH. */
	private static final String JSONPATH = "json/servidor/";

	
	
	
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void test2SalvarEmergencia() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-emergencia" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-Emergencia.json")));
		
		result.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$.resultado.id", is(not(nullValue()))))
			.andExpect(jsonPath("$.mensagens", hasSize(1)))
			.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void test2PesquisarServidorId() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE + "/buscar-servidor")
			.param("value", "AAAABBB")
		).andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect( jsonPath("$.resultado.cpf", is("11111111111")));
	}
	
	
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void test3pesquisarEmergencia() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE + "/emergencia/AAAABBB")
		).andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect( jsonPath("$.resultado.contatos[0].grauParentesco", is(1)))
			.andExpect( jsonPath("$.resultado.contatos[0].nomePessoa", is("Pedro")
			
					));
	}
	
	
	/**
	 * Sucesso consultar manual
	 * @throws Exception the exception
	 */
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void sucessoConsultar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE)
			.param("page", "1")
			.param("count", "10")
			.param("nome", "Mario Melo")
		).andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect( jsonPath("$.resultado.dados[*].nome", todosOsItens(containsString("Mario Melo") ) ));
	}
	
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void altoCompleteNomeServidor() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE + "/nome-servidor")
			.param("value", "melo")
		).andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect( jsonPath("$.resultado", todosOsItens(containsString("Mario Melo") ) ));
	}
	
	/**
	 * Erro validacao.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void erroValidacao() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE)
			.param("page", "1")
			.param("count", "10")
		).andDo( print() )
			.andExpect( status().isBadRequest() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect(jsonPath("$.mensagens[*].codigo", todosOsItens(equalTo(MensagemEnum.M004.name()))));
	}
	
	/**
	 * Erro consultar o manual.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void erroConsultar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform( get(RESOURCE)
			.param("nome", "dsadsadsadsadsa")
			.param("page", "1")
			.param("count", "10")
		).andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
			.andExpect( jsonPath("$.resultado.total", is(0) ));
	}
	
	/**
	 * Salvar dados.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void test0SalvarDados() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/cadastrar-dados-pessoais" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-sucesso.json")));
		
		result.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.resultado.id", is(not(nullValue()))))
			.andExpect(jsonPath("$.mensagens", hasSize(1)))
			.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	/**
	 * Salvar dados erro CPF.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test1SalvarDadosErroCPF() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/cadastrar-dados-pessoais" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-sucesso.json")));
		
		result.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.mensagens", hasSize(1)))
			.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M006.name())));
	}
	
	/**
	 * Salvar graduacao.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test1GraduacaoSalvar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-graduacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-graduacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
		
	}
	
	
	@Test
	public void test2GraduacaoAlterarExcluir() throws Exception { // NOPMD(Testes Unitários) by mario.melo
	
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-graduacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-graduacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
		
		//exluir idioma alterado
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/graduacao/50/servidor/AAAABBB" )
                .contentType(MediaType.APPLICATION_JSON)
               );
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
		
	}
	
	@Test
	public void test3BuscarGraduacoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/buscar-graduacoes-servidor" ).param("value", "50")
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].comprovacao", todosOsItens(containsString("2") ) ));
	}
	
	@Test
	public void test4BuscarPosGraduacoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/buscar-pos-graduacoes-servidor" ).param("value", "50")
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].comprovacao", todosOsItens(containsString("2") ) ));
	}
	
	
	/**
	 * Salvar graduacao.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test1IdiomaSalvar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-idioma" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-idioma.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
		
	}
	
	@Test
	public void test2IdiomaAlterarExcluir() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-idioma" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-idioma.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
		
		//exluir idioma alterado
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/idioma/50/servidor/AAAABBB" )
                .contentType(MediaType.APPLICATION_JSON)
               );
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
		
	}
	
	@Test
	public void test1SalvarPublicacao() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-publicacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-publicacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test2PublicacaoAlterarExcluir() throws Exception { 
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-publicacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-publicacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
		
		//exluir idioma alterado
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/publicacao/50/servidor/AAAABBB" )
                .contentType(MediaType.APPLICATION_JSON)
               );
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
		
	}
	
	@Test
	public void test3BuscarPublicacoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/publicacoes/50" )
                .contentType(MediaType.APPLICATION_JSON));
		
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].descricao", todosOsItens(containsString("teste") ) ));
	}
	
	@Test
	public void test1SalvarEstagio() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-estagio" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-estagio.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}

	@Test
	public void test2BuscarAvaliacoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/avaliacoes/50" )
                .contentType(MediaType.APPLICATION_JSON));
		
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].descricao", todosOsItens(containsString("teste") ) ));
	}
	
	@Test
	public void test1SalvarCapacitacao() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-capacitacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-capacitacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test2AlterarCapacitacao() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-capacitacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-capacitacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test4DeleteCapacitacao() throws Exception {
	
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/capacitacao/50/servidor/AAAABBB" )
                .contentType(MediaType.APPLICATION_JSON)
               );
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
	}
	
	
	@Test
	public void test1SalvarAfastamentoSuspensao() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-afastamento" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-afastamento.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test1SalvarAfastamentoInterrompido() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-afastamento" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-afastamento.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test1SalvarProgressaoSuspenso() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-progressao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-progressao.json")));
		
		
		result.andDo(print()) 
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	
	
	@Test
	public void test1SalvarAfastamentoSuspensaoPosInterrompido() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-afastamento" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-afastamento.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	
	@Test
	public void test2AlterarAfastamento() throws Exception {
	
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-afastamento" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-afastamento.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name()))); 
	}
	
	@Test
	public void test2BuscarAfastamentos() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/buscar-afastamentos-servidor" ).param("value", "AAAABBB")
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].idServidor", todosOsItens(containsString("AAAABBB") ) ));
	}
	
	
	
	@Test
	public void test4DeleteAfastamentoAfastamento() throws Exception {
	
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/afastamento/50/servidor/AAAABBB" )
                .contentType(MediaType.APPLICATION_JSON)
               );
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
	}
	
	@Test
	public void test2BuscarProgressoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/buscar-progressoes-servidor" ).param("value", "AAAABBB")
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect( jsonPath("$.resultado[*].idServidor", todosOsItens(containsString("AAAABBB") ) ));
	}
	
	@Test
	public void test3AlterarProgressao() throws Exception {
	
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-progressao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-progressao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name()))); 
	}
	
	@Test
	public void test1SalvarLotacaoDesligada() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-lotacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "salvar-lotacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test1SalvarLotacaoNaoDesligada() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-lotacao" )
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJsonPath(JSONPATH + "salvar-lotacao-sem-desligamento.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test2BuscarLotacoes() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/lotacao/2550" )
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}
	
	@Test
	public void test3AlterarLotacao() throws Exception {
	
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-lotacao" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(obterJsonPath(JSONPATH + "alterar-lotacao.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name()))); 
	}
	
	@Test
	public void test4DeleteLotacao() throws Exception {
	
		ResultActions result2 = getMockMvc().perform(delete( RESOURCE + "/lotacao/2550/servidor/11"));
		
		
		result2.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));
	}
	
	@Test
	public void test1SalvarObservacaoDesempenho() throws Exception {
		ResultActions result = getMockMvc().perform(post( RESOURCE + "/salvar-observacao-desempenho" )
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJsonPath(JSONPATH + "salvar-observacao-desempenho.json")));
		
		
		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}
	
	@Test
	public void test2buscarObservacoesDesempenho() throws Exception {
		ResultActions result = getMockMvc().perform(get( RESOURCE + "/observacoes-desempenho/1" )
                .contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}
	
}
//CHECKSTYLE:ON