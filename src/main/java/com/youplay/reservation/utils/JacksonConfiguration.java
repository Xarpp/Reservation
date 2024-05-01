package com.youplay.reservation.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Формат для отправки
        DateTimeFormatter sendFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTimeSerializer sendSerializer = new LocalDateTimeSerializer(sendFormatter);

        // Формат для получения
        DateTimeFormatter receiveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTimeDeserializer receiveDeserializer = new LocalDateTimeDeserializer(receiveFormatter);

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, sendSerializer);
        module.addDeserializer(LocalDateTime.class, receiveDeserializer);
        mapper.registerModule(module);

        return mapper;
    }
}
