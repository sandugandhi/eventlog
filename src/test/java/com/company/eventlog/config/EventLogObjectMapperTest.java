package com.company.eventlog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EventLogObjectMapperTest {

    private static final Logger log = LoggerFactory.getLogger(EventLogObjectMapperTest.class.getName());

    private
    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - should execute once before all test methods in this class");
    }

    @DisplayName("test ObjectMapper")
    @Test
    void should_return_ObjectMapper(){
        //given
        EventLogObjectMapper eventLogObjectMapper = new EventLogObjectMapper();

        // when
        Optional<ObjectMapper> objectMapper = Optional.ofNullable(eventLogObjectMapper.getObjectMapper());

        //then
        Assertions.assertTrue(objectMapper.isPresent());

    }
}
