package br.gov.anvisa.sgc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;

import br.gov.anvisa.base.comum.exception.ComumException;

/**
 * The Class DataUtil.
 */
public final class DataUtil {
	
	/**
	 * Instantiates a new data util.
	 */
	private DataUtil() {
	}

	/** The Constant FORMATO. */
	private static final String FORMATO = "dd/MM/yyyy";
	
	/** The Constant MSG. */
	private static final String MSG = "Data inválida";
	
	/** The locale. */
	private static Locale locale = new Locale("pt", "BR");

	/**
	 *  Método que retorna uma data incial para uma consulta HQL, usando na clásula between  
	 * para obter os resultados corretos por conta dos campos do tipo datetime. 
	 *
	 * @param data the data
	 * @return retorna a dataInicial preenchiada com horário default para consulta
	 */  
    public static Date getDataInicial(Date data) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(data);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        return cal.getTime();  
    }  
      
    /**
     *  
     * Método que retorna uma data final para uma consulta HQL, usando na clásula between  
     * para obter os resultados corretos por conta dos campos do tipo datetime. 
     *
     * @param data the data
     * @return retorna a dataFinal preenchiada com horário default para consulta
     */  
    public static Date getDataFinal(Date data) {  
    	final int hora = 23;
    	final int minuto = 59;
    	final int segundo = 59;
    	Calendar cal = Calendar.getInstance();  
        cal.setTime(data);  
		cal.set(Calendar.HOUR_OF_DAY, hora);  
		cal.set(Calendar.MINUTE, minuto);  
		cal.set(Calendar.SECOND, segundo);  
        return cal.getTime();  
    }  
    
    /**
     * Método que verifica se a data é valida.
     *
     * @param data the data
     * @return Boolean
     */
	public static Boolean isDataValida(Date data){
		if(data == null){
			throw new ComumException(MSG);
		}
		
		String dataFormatada = new SimpleDateFormat(FORMATO, locale).format(data);
		
		 Pattern p = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		 Matcher m = p.matcher(dataFormatada);
		 return m.matches();
	}
	
	/**
	 * Formata a data com o padrão dd/MM/yyyy.
	 *
	 * @param data the data
	 * @return String
	 */
	public static String getDataFormatada(Date data){
		if(data == null){
			throw new ComumException(MSG);
		}
		
		return new SimpleDateFormat(FORMATO, locale).format(data);
	}
	
	/**
	 * Formata período.
	 *
	 * @param inicio the inicio
	 * @param fim the fim
	 * @return the string
	 */
	public static String periodoPorExtenso(Calendar inicio, Calendar fim) {
		String retorno = "";
		
		if ( isMesmoDia(inicio, fim) ) {
			retorno = new SimpleDateFormat("d 'de' MMMM 'de' yyyy").format(inicio.getTime());
		} else {
			String formatoInicio = "d ";
			
			if ( !isMesmoMes(inicio, fim) ) {
				formatoInicio += "'de' MMMM ";
			}
			
			if ( !isMesmoAno(inicio, fim) ) {
				formatoInicio += "'de' yyyy ";
			}
			
			String separador = isDiaSeguido(inicio, fim) ? "e":"a";
			
			String textoInicio = new SimpleDateFormat(formatoInicio).format(inicio.getTime());
			String textoFim = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy").format(fim.getTime());
			
			retorno = textoInicio.concat(separador).concat(textoFim);
		}
		
		return retorno;
	}

	/**
	 * Checks if is dia seguido.
	 *
	 * @param inicio the inicio
	 * @param fim the fim
	 * @return true, if is dia seguido
	 */
	public static boolean isDiaSeguido(Calendar inicio, Calendar fim) {
		Calendar aux = Calendar.getInstance();
		aux.setTimeInMillis(inicio.getTimeInMillis());
		
		aux.add(Calendar.DAY_OF_MONTH, 1);
		
		return isMesmoDia(aux, fim);
	}

	/**
	 * Checks if is mesmo dia.
	 *
	 * @param inicio the inicio
	 * @param fim the fim
	 * @return true, if is mesmo dia
	 */
	public static boolean isMesmoDia(Calendar inicio, Calendar fim) {
		return isMesmoAno(inicio, fim)
			&& isMesmoMes(inicio, fim)
			&& inicio.get(Calendar.DAY_OF_MONTH) == fim.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Checks if is mesmo mes.
	 *
	 * @param inicio the inicio
	 * @param fim the fim
	 * @return true, if is mesmo mes
	 */
	public static boolean isMesmoMes(Calendar inicio, Calendar fim) {
		return isMesmoAno(inicio, fim)
			&& inicio.get(Calendar.MONTH) == fim.get(Calendar.MONTH);
	}
	
	/**
	 * Checks if is mesmo ano.
	 *
	 * @param inicio the inicio
	 * @param fim the fim
	 * @return true, if is mesmo ano
	 */
	public static boolean isMesmoAno(Calendar inicio, Calendar fim) {
		return inicio.get(Calendar.YEAR) == fim.get(Calendar.YEAR);
	}
	
	public static LocalDate converterStringParaLocalDate(String data){
		if(data == null){
			
			return null;
			
		}
		
		LocalDate localData = new LocalDate(data);
		
		return localData;
	}
	
	public static String converterLocalDateParaString(LocalDate data){
		if(data == null){
			return null;
		}
		return data.toString();
	}

	public static Integer getAnoAtual() {
		return new LocalDate().getYear();
	}
	
}
