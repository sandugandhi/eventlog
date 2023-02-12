package com.company.eventlog.config;

import com.company.eventlog.domain.EventLog;
import com.company.eventlog.domain.EventLogDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventLogObjectMapper {

    @Bean
    public ObjectMapper getObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(EventLog.class, new EventLogDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
