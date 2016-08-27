/*
* Projeto: ouvidorsus-web
* Arquivo: TestUtil.java
* 
* Copyright @ Ministério da Saúde.
*
* Este software é confidencial e de propriedade do Ministério da Saúde.
* Não é permitida sua distribuição ou divulgação do seu conteúdo sem 
* expressa autorização do mesmo.
*/
package br.gov.anvisa.sgc.test.comum;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import br.gov.anvisa.sgc.entidades.dbSeguranca.Usuario;
import br.gov.anvisa.sgc.negocio.seguranca.UsuarioComumToken;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;


/**
 * Classe utilitária para a execução de testes unitários.
 *
 * @author mario.melo
 * Criado em: 18/05/2015
 */
public final class TestUtil {
	
	private static final String ERRO_AO_PROCESSAR_JSON = "Erro ao processar JSON";

	/**
	 * Instantiates a new test util.
	 */
	private TestUtil(){
	}

	/** The Constant ROLE. */
	public static final String ROLE = "ROLE_TEST";
	
	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(TestUtil.class);
	
	/** The class loader. */
	private static ClassLoader classLoader = TestUtil.class.getClassLoader(); 

    /**
     * Convert object to form url encoded bytes.
     *
     * @param object the object
     * @return the byte[]
     */
    public static byte[] convertObjectToFormUrlEncodedBytes(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        @SuppressWarnings("unchecked")
		Map<String, Object> propertyValues = mapper.convertValue(object, Map.class);

        Set<String> propertyNames = propertyValues.keySet();
        Iterator<String> nameIter = propertyNames.iterator();

        StringBuilder formUrlEncoded = new StringBuilder();

        for (int index=0; index < propertyNames.size(); index++) {
            String currentKey = nameIter.next();
            Object currentValue = propertyValues.get(currentKey);

            formUrlEncoded.append(currentKey);
            formUrlEncoded.append("=");
            formUrlEncoded.append(currentValue);

            if (nameIter.hasNext()) {
                formUrlEncoded.append("&");
            }
        }

        return formUrlEncoded.toString().getBytes();
    }
    
    /**
     * Criar token.
     *
     * @param usuarioAutenticado the usuario autenticado
     * @param auths the auths
     * @return the usuario ouvidor token
     */
    public static UsuarioComumToken criarToken(Usuario usuarioAutenticado, List<GrantedAuthority> auths) {

    	UsuarioComumToken token = null;
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		
		autorizacoes.add(new SimpleGrantedAuthority("ROLE_CLIENT_OAUTH"));
		autorizacoes.addAll(auths);
		token = new UsuarioComumToken(usuarioAutenticado.getId(), null, autorizacoes);			

		token.setEmail(usuarioAutenticado.getEmail());
		token.setCpf(usuarioAutenticado.getCpf());
		token.setIdUsuarioAutenticado(usuarioAutenticado.getId());
		token.setNome(usuarioAutenticado.getNome());
		
		return token;
	}
    
    /**
     * Convert object to json.
     *
     * @param object the object
     * @return the string
     */
    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = obterMapper();

        String retorno = null;
		try {
			retorno = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.error(ERRO_AO_PROCESSAR_JSON, e); 
		}

        return retorno;
    }
    
    /**
     * Obter json path.
     *
     * @param pathJson the path json
     * @return the string
     */
    public static String obterJsonPath(String pathJson) {
    	Assert.notNull(pathJson);
    	
    	String retorno = null;
		try {
			InputStream  is = classLoader.getResourceAsStream(pathJson);
			retorno = IOUtils.toString(is,"UTF-8");
		} catch (IOException e) {
			LOGGER.error(ERRO_AO_PROCESSAR_JSON, e);
		}

        return retorno;
    }
    
    /**
     * Obter mapper.
     *
     * @return the object mapper
     */
    private static ObjectMapper obterMapper(){
    	ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        Hibernate4Module modulo = new Hibernate4Module();
		modulo.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		
	    mapper.registerModule(modulo);

	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setTimeZone(TimeZone.getDefault());
//	    
//	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//	    mapper.setDateFormat(dateFormat);
	    
	    return mapper;
    }

    /**
     * Cria string with length.
     *
     * @param length the length
     * @return the string
     */
    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }
    
	/**
	 * Convert json to object.
	 *
	 * @param <T> the generic type
	 * @param result the result
	 * @param class1 the class1
	 * @return the object
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertJsonToObject(ResultActions result,
			Class<T> class1) throws Exception { // NOPMD by mario.melo on
		 ObjectMapper mapper = obterMapper();
		 
		 Object obj = null;
	    	
	    	try{
	        	jsonPath("$.resultado", is(false)).match(result.andReturn());
	        }catch(AssertionError asserts){
	        	try {
				obj = mapper.readValue(
						StringUtils.removeEnd(
								StringUtils.substringAfterLast(
										asserts.getMessage(), "<"), ">"),
						class1);
				} catch (JsonProcessingException e) {
					LOGGER.error(ERRO_AO_PROCESSAR_JSON, e);
				} catch (IOException e) {
					LOGGER.error(ERRO_AO_PROCESSAR_JSON, e);
				}
	        }
	        return (T) obj;
	}
}
