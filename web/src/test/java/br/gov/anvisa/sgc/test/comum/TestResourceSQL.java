package br.gov.anvisa.sgc.test.comum;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * The Enum TestResourceSQL.
 */
public enum TestResourceSQL {
	
	/** The limpa tabelas config questionario. */
	
	
	SERVIDOR(1,"classpath:sql/Servidor/servidor.sql");
	
//	LIMPA_TABELAS_CONFIG_QUESTIONARIO("classpath:sql/configuracao-questionario/LIMPA_TABELAS_CONFIG_QUESTIONARIO.sql"),
//	
//	INIT_CONFIGURACAO("classpath:sql/configuracao-questionario/INIT_CONFIGURACAO.sql"),
//	
//	/** The limpar reuniao. */
//	LIMPAR_REUNIAO("classpath:sql/reuniao-sensibilizacao/LIMPAR_REUNIAO.sql"),
//	
//	/** The dml ouvidor. */
//	DML_OUVIDOR(1,"classpath:sql/DML_OUVIDOR.sql"),
//	
//	CONFIG_QUES(2,"classpath:sql/configuracao-questionario/INIT_CONFIGURACAO.sql"),
//	
//	/** The orgao. */
//	ORGAO(3,"classpath:sql/manter-orgao/ORGAO.sql"),
//	
//	/** The ouvidoria. */
//	OUVIDORIA(4,"classpath:sql/manter-ouvidoria/OUVIDORIA.sql"),
//	
//	/** The disponibilizar ficha cadastro. */
//	DISPONIBILIZAR_FICHA_CADASTRO(5,"classpath:sql/ficha-cadastro/DISPONIBILIZAR_FICHA_CADASTRO.sql"),
//	
//	/** The ficha cadastro. */
//	FICHA_CADASTRO(6,"classpath:sql/ficha-cadastro/FICHA_CADASTRO.sql"),
//	
//	/** The ficha cadastro membro. */
//	FICHA_CADASTRO_MEMBRO(7,"classpath:sql/ficha-cadastro/FICHA_CADASTRO_MEMBRO.sql"),
//	
//	/** The reuniao. */
//	REUNIAO(8,"classpath:sql/reuniao-sensibilizacao/REUNIAO.sql"),
//	
//	/** The disponibilizar plano acao. */
//	DISPONIBILIZAR_PLANO_ACAO(9,"classpath:sql/disponibilizar-plano-acao/DISPONIBILIZAR_PLANO_ACAO.sql"),
//	
//	/** The parecer tecnico. */
//	PARECER_TECNICO(10, "classpath:sql/gerar-parecer-tecnico/PARECER_TECNICO.sql"),
//	
//	/** The capacitacao. */
//	CAPACITACAO(11, "classpath:sql/capacitacao/CAPACITACAO.sql"),
//	
//	/** The manual. */
//	MANUAL(12, "classpath:sql/manual/MANUAL.sql"),
//	
//	/** The assinatura. */
//	ASSINATURA(13, "classpath:sql/assinatura/ASSINATURA.sql"),
//	
//	/** The item. */
//	ITEM(14, "classpath:sql/manter-item/ITEM.sql"),
//	
//	/** The kit. */
//	KIT(15, "classpath:sql/manter-kit/KIT.sql"),
//	
//	/** The rede DOGES. */
//	REDE_DOGES(16, "classpath:sql/manter-rede/REDE_DOGES.sql"),
//	
//	/** The rede REDE_HOSP_FED. */
//	REDE_HOSP_FED(17, "classpath:sql/manter-rede/OUVIDORIAS_HOSP_FEDERAIS.sql"),
//	
//	/** The rede REDE_SEC_ESTADUAIS. */
//	REDE_SEC_ESTADUAIS(18, "classpath:sql/manter-rede/OUVIDORIAS_SEC_ESTADUAIS.sql"),
//	
//	/** The rede REDE_SEC_MUNICIPAIS. */
//	REDE_SEC_MUNICIPAIS(19, "classpath:sql/manter-rede/OUVIDORIAS_SEC_MUNICIPAIS.sql"),
//	
//	/** The rede REDE_REG_ESTADUAIS. */
//	REDE_REG_ESTADUAIS(20, "classpath:sql/manter-rede/OUVIDORIAS_REG_ESTADUAIS.sql"),
//	
//	/** The rede REDE_REG_MUNICIPAIS. */
//	REDE_REG_MUNICIPAIS(21, "classpath:sql/manter-rede/OUVIDORIAS_REG_MUNICIPAIS.sql"),
//	
//	/** The rede REDE_HOSP_ESTADUAIS. */
//	REDE_HOSP_ESTADUAIS(22, "classpath:sql/manter-rede/OUVIDORIAS_HOSP_ESTADUAIS.sql"),
//	
//	/** The rede REDE_HOSP_ESTADUAIS. */
//	REDE_HOSP_MUNICIPAIS(23, "classpath:sql/manter-rede/OUVIDORIAS_HOSP_MUNICIPAIS.sql"),
//	
//	/** The subredes e sistema integrador **/
//	REDE_HOSP_MUNI(24,"classpath:sql/manter-rede/REDE_HOSP_MUNICIPAIS.sql"),
//	
//	/** The subredes e sistema integrador **/
//	SUBREDE_SISTEMA_INTEGRADOR(25,"classpath:sql/manter-rede/SUBREDE_SISTEMA_INTEGRADOR.sql"),
//	
//	OUVIDORIAS_UNIDADES_AREAS_MS(26,"classpath:sql/manter-rede/OUVIDORIAS_UNIDADES_AREAS_MS.sql"),
//	
//	/** The apoio. */
//	APOIO(27, "classpath:sql/manter-apoio/APOIO.sql");
	
	/** The path. */
	private String path;
	
	/** The ordem execucao. */
	private Integer ordemExecucao;
	
	/**
	 * Instantiates a new test resource sql.
	 *
	 * @param ordemExecucao the ordem execucao
	 * @param path the path
	 */
	TestResourceSQL(Integer ordemExecucao, String path){
		this.ordemExecucao = ordemExecucao;
		this.path = path;
	}
	
	/**
	 * Instantiates a new test resource sql.
	 *
	 * @param path the path
	 */
	TestResourceSQL(String path){
		
		this.path = path;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the ordem execucao.
	 *
	 * @return the ordem execucao
	 */
	public Integer getOrdemExecucao() {
		return ordemExecucao;
	}
	
	/**
	 * Gets the resources por ordem execucao.
	 *
	 * @return the resources por ordem execucao
	 */
	public static Collection<TestResourceSQL> getResourcesPorOrdemExecucao(){
		
		SortedMap<Integer, TestResourceSQL> map = new TreeMap<Integer, TestResourceSQL>();
		
		for (TestResourceSQL res : TestResourceSQL.values()) {
			
			if (res.getOrdemExecucao() == null){
				continue;
			}
			
		    map.put(res.getOrdemExecucao(), res);
		}
		return map.values();
	}
}