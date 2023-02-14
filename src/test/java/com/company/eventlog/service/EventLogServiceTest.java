package com.company.eventlog.service;

import com.company.eventlog.config.EventLogObjectMapper;
import com.company.eventlog.domain.EventLog;
import com.company.eventlog.repository.EventLogRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:application-integrationtest.properties")
public class EventLogServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EventLogServiceTest.class.getName());

    EventLogRepository eventLogRepository;

    EventLogService eventLogService;

    EventLogObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        log.info("@BeforeAll - should execute once before all test methods in this class");
    }

    @BeforeEach
    public void setupEachTest(){
        eventLogService = new EventLogService(eventLogRepository,objectMapper);
    }


    @DisplayName("test for invalid input file path")
    @Test
    public void process_fail_for_invalid_file() {
        String file="blah blah";
        Assertions.assertThrows(IOException.class, () -> eventLogService.process(file));
    }

    @DisplayName("test for invalid input file content")
    @Test
    public void process_fail_for_invalid_content() {
        String file="./src/test/resources/invalid_content.logs";
        Assertions.assertThrows(RuntimeException.class, () -> eventLogService.process(file));
    }


    @DisplayName("test for valid input file content")
    @Test
    @Disabled
    public void process_success_for_valid_content() throws IOException{
        //given
        String file="./src/test/resources/valid_content.logs";

        //when
        eventLogService.process(file);
        //then
        Optional<EventLog> eventLog= eventLogRepository.findById("scsmbstgre");
        Assertions.assertTrue(eventLog.isPresent());
        Assertions.assertEquals(3,eventLog.get().getDuration());
    }





}
