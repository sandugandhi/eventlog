package com.company.eventlog.service;

import com.company.eventlog.config.EventLogObjectMapper;
import com.company.eventlog.domain.EventLog;
import com.company.eventlog.repository.EventLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
public class EventLogService {

    private static final long THRESHOLD = 4;
    private static final Logger logger = LoggerFactory.getLogger(EventLogService.class);

    @Autowired
    private EventLogObjectMapper objectMapper;

    @Autowired
    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository,EventLogObjectMapper objectMapper){
        this.eventLogRepository=eventLogRepository;
        this.objectMapper=objectMapper;
    }

    public EventLog save(EventLog eventLog) {
        return this.eventLogRepository.save(eventLog);

    }

    public Iterable<EventLog> list(){
        return this.eventLogRepository.findAll();
    }

    public void process(String filepath) throws IOException {

        Path filePath = Paths.get(filepath);
        HashMap<String, EventLog> hashMap = new HashMap<>();

        try (Stream<String> lines = Files.lines(filePath)) {
            lines.map(l -> {
                try {
                    return objectMapper.getObjectMapper().readValue(l, EventLog.class);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).forEach(
                    eventLog -> {
                        if (hashMap.containsKey(eventLog.getId())) {
                            EventLog existingEL = hashMap.remove(eventLog.getId());
                            existingEL.merge(eventLog);
                            long duration = existingEL.getEnd() - existingEL.getStart();
                            if (duration > THRESHOLD) {
                                logger.warn("Flag raised for id={} as the duration is {} ms", existingEL.getId(), duration);
                                existingEL.setFlag(Boolean.TRUE);
                            }
                            existingEL.setDuration(duration);
                            this.save(existingEL);
                        } else {
                            hashMap.put(eventLog.getId(), eventLog);
                        }
                    }
            );
        }
    }

}
