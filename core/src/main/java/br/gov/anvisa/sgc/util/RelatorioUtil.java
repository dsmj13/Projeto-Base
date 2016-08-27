package br.gov.anvisa.sgc.util;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
//import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
//import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import br.gov.anvisa.sgc.dominio.TipoRelatorioEnum;


/**
 * Classe utilitária para exibição dos relatórios jasper.
 *
 * @author marcelo.nogueira
 * Criado em: 16/05/2014
 */
public class RelatorioUtil {
//
//	private static final String ATTACHMENT_FILENAME = "attachment; filename=\"";
//
//	private static final String CONTENT_DISPOSITION = "Content-Disposition";
//
//	/** The Constant LOGGER. */
//	private static final Log LOGGER = LogFactory.getLog(RelatorioUtil.class);
//	
//	/** The Constant PATH_RELATORIOS. */
//	private static final String PATH_RELATORIOS = File.separator + "WEB-INF" +
//			File.separator + "classes" +
//			File.separator + "relatorios" + File.separator;
//	
//	/** The Constant PATH_IMAGENS. */
//	public static final String PATH_IMAGENS = File.separator + "assets" +
//			File.separator + "images" + File.separator;
//	
//	/** The response. */
//	private HttpServletResponse response;
//	
//	/** The request. */
//	private HttpServletRequest request;
//
//	/**
//	 * Construtor do relatório .
//	 *
//	 * @param request {@link HttpServletRequest}
//	 * @param response {@link HttpServletResponse}
//	 */
//	public RelatorioUtil(HttpServletRequest request, 
//			HttpServletResponse response){
//		this.request = request;
//		this.response = response;
//	}
//	
//	/**
//	 * Construtor do relatório sem response.
//	 *
//	 * @param request {@link HttpServletRequest}
//	 */
//	public RelatorioUtil(HttpServletRequest request){
//		this.request = request;
//	}
//	
//	
//	/**
//	 * Método que gera o relatório.
//	 *
//	 * @param pathJasper Path para o arquivos jasper
//	 * @param nomeArquivo Nome para o arquivo de exportação
//	 * @param tipoRelatorio Tipo do relatório {@link TipoRelatorioEnum}
//	 * @param parametros Mapa de parâmetros para ser inserido no relatório
//	 * @param dados {@link Collection} que servirá de data source do relatório
//	 */
//	public void gerarRelatorio(String pathJasper, String nomeArquivo, 
//			TipoRelatorioEnum tipoRelatorio, Map<String, Object> parametros,
//			Collection<?> dados){
//		
//		setarParametros(parametros);
//		
//		setarLocale();
//		
//		String pathCtx = recuperaContexto();
//		
//		ServletOutputStream outputStream = null;
//		try {
//			outputStream = response.getOutputStream();
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage(),e);
//		}
//		
//		response.setHeader("Cache-Control", "max-age=60");
//		
//		switch (tipoRelatorio) {
//		case PDF:
//			response.setContentType("application/pdf");
//			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + nomeArquivo + ".pdf\"");
//			break;
//		case XLS:
//			response.setContentType("application/vnd.ms-excel");
//			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + nomeArquivo + ".xls\"");
//			break;
//		default:
//			break;
//		}
//		
//		gerarRelatorio(pathCtx.concat(File.separator).concat(pathJasper), 
//				tipoRelatorio, 
//				parametros, 
//				dados, outputStream);
//	}
//
//	public void gerarRelatorio(String pathJasper, String nomeArquivo, 
//			TipoRelatorioEnum tipoRelatorio, Map<String, Object> parametros){
//		
//		setarLocale();
//		
//		setarParametros(parametros);
//		
//		String pathCtx = recuperaContexto();
//		
//		ServletOutputStream outputStream = null;
//		try {
//			outputStream = response.getOutputStream();
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage(),e);
//		}
//		
//		response.setHeader("Cache-Control", "max-age=60");
//		
//		switch (tipoRelatorio) {
//		case PDF:
//			response.setContentType("application/pdf");
//			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + nomeArquivo + ".pdf\"");
//			break;
//		case XLS:
//			response.setContentType("application/vnd.ms-excel");
//			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + nomeArquivo + ".xls\"");
//			break;
//		default:
//			break;
//		}
//		
//		gerarRelatorio(pathCtx.concat(File.separator).concat(pathJasper), 
//				tipoRelatorio, 
//				parametros,  outputStream);
//	}
//
//	private void setarLocale() {
//		Locale.setDefault(new Locale("pt", "BR"));
//	}
//	
//	/**
//	 * Recupera contexto.
//	 *
//	 * @return the string
//	 */
//	private String recuperaContexto() {
//		return request.getServletContext().getRealPath(PATH_RELATORIOS);
//	}
//
//	/**
//	 * Setar parametros.
//	 *
//	 * @param parametros the parametros
//	 */
//	private void setarParametros(Map<String, Object> parametros) {
//		String pathImagens = request.getServletContext().getRealPath(PATH_IMAGENS);
//		
//		parametros.put("PATH_RELATORIOS", recuperaContexto());
//		
//		parametros.put("brasao", pathImagens.concat(File.separator).concat("brasao_pb1.gif"));
//	}
//
//	/**
//	 * Gerar relatorio.
//	 *
//	 * @param pathJasper the path jasper
//	 * @param tipoRelatorio the tipo relatorio
//	 * @param parametros the parametros
//	 * @param dados the dados
//	 * @param out the out
//	 */
//	private void gerarRelatorio(String pathJasper,
//			TipoRelatorioEnum tipoRelatorio, Map<String, Object> parametros,
//			Collection<?> dados, OutputStream out) {
//
//		JasperPrint print = gerarJasperPrint(pathJasper, parametros, dados);
//
//		if (print != null) {
//			try {
//				switch (tipoRelatorio) {
//				case PDF:
//					gerarPDF(out, print);
//					break;
//
//				case XLS:
//					gerarXLS(out, print);
//					break;
//
//				default:
//					break;
//				}
//
//			} catch (JRException e) {
//				LOGGER.error(e.getMessage(), e);
//				try {
//					out.flush();
//					out.close();
//				} catch (IOException ioe) {
//					LOGGER.error(e.getMessage(), ioe);
//					// erro ao fechar o stream
//				}
//			} finally {
//				try {
//					out.flush();
//					out.close();
//				} catch (IOException ioe) {
//					LOGGER.error(ioe.getMessage(), ioe);
//					// erro ao fechar o stream
//				}
//			}
//		} else {
//			LOGGER.error(String.format("Não conseguiu recuperar o jasper - %s",
//					pathJasper));
//		}
//	}
//
//	private void gerarRelatorio(String pathJasper,
//			TipoRelatorioEnum tipoRelatorio, Map<String, Object> parametros,
//			OutputStream out) {
//		
//		JasperPrint print = gerarJasperPrint(pathJasper, parametros);
//		
//		if (print != null) {
//			try {
//				switch (tipoRelatorio) {
//				case PDF:
//					gerarPDF(out, print);
//					break;
//					
//				case XLS:
//					gerarXLS(out, print);
//					break;
//					
//				default:
//					break;
//				}
//				
//			} catch (JRException e) {
//				LOGGER.error(e.getMessage(), e);
//				try {
//					out.flush();
//					out.close();
//				} catch (IOException ioe) {
//					LOGGER.error(e.getMessage(), ioe);
//					// erro ao fechar o stream
//				}
//			} finally {
//				try {
//					out.flush();
//					out.close();
//				} catch (IOException ioe) {
//					LOGGER.error(ioe.getMessage(), ioe);
//					// erro ao fechar o stream
//				}
//			}
//		} else {
//			LOGGER.error(String.format("Não conseguiu recuperar o jasper - %s",
//					pathJasper));
//		}
//	}
//
//	/**
//	 * Gerar jasper print.
//	 *
//	 * @param pathJasper the path jasper
//	 * @param parametros the parametros
//	 * @param dados the dados
//	 * @return the jasper print
//	 */
//	private JasperPrint gerarJasperPrint(String pathJasper,
//			Map<String, Object> parametros, Collection<?> dados) {
//		
//		JasperPrint print = null;
//		
//		try {
//			print = JasperFillManager.fillReport(pathJasper, parametros,
//					new JRBeanCollectionDataSource(dados));
//		} catch (JRException e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		return print;
//	}
//
//	private JasperPrint gerarJasperPrint(String pathJasper,
//			Map<String, Object> parametros) {
//		
//		JasperPrint print = null;
//		
//		try {
//			print = JasperFillManager.fillReport(pathJasper, parametros,
//					new JREmptyDataSource());
//		} catch (JRException e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		return print;
//	}
//	
//	private void gerarXLS(OutputStream out, JasperPrint print)
//			throws JRException {
//		JRXlsExporter xlsExporter = new JRXlsExporter();
//		xlsExporter.setExporterInput(new SimpleExporterInput(print));
//		xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
//		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//		configuration.setOnePagePerSheet(true);
//		configuration.setDetectCellType(true);
//		configuration.setCollapseRowSpan(false);
//		xlsExporter.setConfiguration(configuration);
//		xlsExporter.exportReport();
//	}
//
//	private void gerarPDF(OutputStream out, JasperPrint print)
//			throws JRException {
//		JRPdfExporter pdfExporter = new JRPdfExporter();
//
//		pdfExporter.setExporterInput(new SimpleExporterInput(print));
//		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
//		SimplePdfExporterConfiguration configurationPDF = new SimplePdfExporterConfiguration();
//		pdfExporter.setConfiguration(configurationPDF);
//		pdfExporter.exportReport();
//	}
}
