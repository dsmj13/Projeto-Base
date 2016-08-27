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
public class TestInstituicao extends TestBase{


	/** The Constant RESOURCE. */
	private static final String RESOURCE = "/api/intituicao";

	/** The Constant JSONPATH. */
	private static final String JSONPATH = "json/Instituicao/";

	/**
	 *  The Constant JSONPATH.
	 *
	 * @throws Exception the exception
	 */
	//	private static final String JSONPATH = "json/manual/";

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
				.param("descricao", "anvisa")
				).andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
				.andExpect( jsonPath("$.resultado.dados[*].descricao", todosOsItens(containsString("Anvisa") ) ));
	}

	@Test
	public void erroConsultar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform( get(RESOURCE)
				.param("descricao", "dsadsadsadsadsa")
				.param("page", "1")
				.param("count", "10")
				).andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
				.andExpect( jsonPath("$.resultado.total", is(0) ));
	}

	@Test
	@SqlPath(TestResourceSQL.SERVIDOR)
	public void altoCompleteNomeInstituicao() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		getMockMvc().perform(get(RESOURCE + "/nome-instituicao")
				.param("value", "visa")
				).andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( content().contentType(MediaType.APPLICATION_JSON) )
				.andExpect( jsonPath("$.resultado[*].descricao", todosOsItens(containsString("Anvisa") ) ));

	}
	@Test
	public void test0Salvar() throws Exception { // NOPMD(Testes Unitários) by mario.melo
		ResultActions result = getMockMvc().perform(post( RESOURCE  )
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJsonPath(JSONPATH + "salvar-sucesso.json")));

		result.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.M001.name())));
	}


	@Test
	public void test1Excluir() throws Exception { // NOPMD(Testes Unitários) by mario.melo

		ResultActions result2 = getMockMvc().perform(post( RESOURCE  )
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJsonPath(JSONPATH + "salvar-sucesso.json")));
		result2.andDo(print());

		ResultActions result = getMockMvc().perform(delete( RESOURCE + "/50" )
				.contentType(MediaType.APPLICATION_JSON)
				);


		result.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagens", hasSize(1)))
		.andExpect(jsonPath("$.mensagens[0].codigo", is(MensagemEnum.MSG03.name())));

	}
}
//CHECKSTYLE:ON