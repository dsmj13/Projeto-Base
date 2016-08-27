package br.gov.anvisa.sgc.dominio.converter;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateConverter extends JsonSerializer<LocalDate> {

    private static final Logger LOG = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public void serialize(LocalDate value, JsonGenerator jgen,
            SerializerProvider provider) {
        try {
            jgen.writeObject(value.toString());
        } catch (IOException e) {
            LOG.info("Data converter, serializer.", e);
        }
    }
}
