/*
 * Projeto: ouvidorsus-web
 * Arquivo: InjectDataTestExecutionListener.java
 * 
 * Copyright @ Ministério da Saúde.
 *
 * Este software é confidencial e de propriedade do Ministério da Saúde.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
 * expressa autorização do mesmo.
 */
package br.gov.anvisa.sgc.test.comum;

import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.jdbc.JdbcTestUtils;

import br.gov.anvisa.sgc.comum.anotacao.SqlPath;


/**
 * Classe utilitária de execução de scripts SQL nos testes unitários .
 *
 * @author marcelo.nogueira Criado em: 24/04/2014
 */
@Component
public class ExecucaoScriptListener extends
		DependencyInjectionTestExecutionListener {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ExecucaoScriptListener.class);

	/** The jdbc template. */
	private static JdbcTemplate jdbcTemplate;
	
	/** The datasource. */
	private static DataSource datasource;
	
	/** The Constant ENCODING. */
	private static final String ENCODING = "UTF-8";

	/**
	 * Execute un éventuel script SQL indiqué via l'annotation {@link SqlPath}
	 * avant l'execution d'un test.
	 *
	 * @param testContext the test context
	 * @throws Exception the exception
	 */
	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception { // NOPMD by andre.minella on 24/09/14 15:01
		super.beforeTestClass(testContext);

		Method myMethdo = testContext.getTestMethod();
		SqlPath dsLocation = myMethdo.getAnnotation(SqlPath.class);
		if (dsLocation != null) {
			LOGGER.info("\nExecutando Script: " + dsLocation.value() + "\n");
			executeSqlScript(testContext, dsLocation.value());
		}
	}

	/**
	 * Inicia dml.
	 *
	 * @param context the context
	 * @param pathScriptDML the path script dml
	 */
	public void initDML(ApplicationContext context, TestResourceSQL pathScriptDML){

		if (pathScriptDML != null) {
			JdbcTemplate jdbcTemplate = getJdbCTemplate(getDatasource(context));
			Resource resource = context.getResource(pathScriptDML.getPath());
			executeSqlScript(jdbcTemplate, new EncodedResource(resource,
					ENCODING));
		}
	}

	/**
	 * Execute un script sur un chemin d'accès au fichier.
	 *
	 * @param testContext            le context du test
	 * @param sqlResourcePath            le chemin du fichier Sql
	 */
	private void executeSqlScript(TestContext testContext,
			TestResourceSQL[] sqlResourcePath) {
		
		JdbcTemplate jdbcTemplate = getJdbCTemplate(getDatasource(testContext));
		
		for (TestResourceSQL resourcePath : sqlResourcePath){
			Resource resource = testContext.getApplicationContext().getResource(resourcePath.getPath());
			executeSqlScript(jdbcTemplate, new EncodedResource(resource, ENCODING));
		}
		
	}

	/**
	 * Gets the datasource.
	 *
	 * @param context the context
	 * @return the datasource
	 */
	private DataSource getDatasource(ApplicationContext context) {
		if (datasource == null) {
			datasource = context.getBean(DataSource.class);
		}
		return datasource;
	}

	/**
	 * Gets the datasource.
	 *
	 * @param testContext the test context
	 * @return the datasource
	 */
	private DataSource getDatasource(TestContext testContext) {
		if (datasource == null) {
			datasource = testContext.getApplicationContext().getBean(
					DataSource.class);
		}
		return datasource;
	}

	/**
	 * Gets the jdb c template.
	 *
	 * @param datasource the datasource
	 * @return the jdb c template
	 */
	private JdbcTemplate getJdbCTemplate(DataSource datasource) {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(datasource);
		}
		return jdbcTemplate;
	}

	/**
	 * Execute une resource via un jdbcTemplate donné.
	 *
	 * @param simpleJdbcTemplate the simple jdbc template
	 * @param resource the resource
	 */
	private static void executeSqlScript(JdbcTemplate simpleJdbcTemplate,
			EncodedResource resource) {

		List<String> statements = new LinkedList<String>();
		try {
			LineNumberReader lnr = new LineNumberReader(resource.getReader());
			String script = JdbcTestUtils.readScript(lnr);
			char delimiter = ';';
			if (!JdbcTestUtils.containsSqlScriptDelimiters(script, delimiter)) {
				delimiter = '\n';
			}
			JdbcTestUtils.splitSqlScript(script, delimiter, statements);
			for (String statement : statements) {
				try {
					simpleJdbcTemplate.update(statement);
				} catch (DataAccessException ex) {
					LOGGER.error(ex.getMessage(),ex);
				}
			}
		} catch (IOException ex) {
			throw new DataAccessResourceFailureException(
					"Impossible d'ouvrir le script depuis " + resource, ex);
		}
	}
}
