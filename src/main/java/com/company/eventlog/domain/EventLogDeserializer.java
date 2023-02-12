package com.company.eventlog.domain;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

public class EventLogDeserializer extends StdDeserializer<EventLog> {

    private static final long serialVersionUID = 1883547683050039861L;

    public EventLogDeserializer() {
        this(null);
    }

    public EventLogDeserializer(final Class<?> vc) {
        super(vc);
    }

    /**
     * {"id":  "scsmbstgra" , "state":  "STARTED" , "type":  "APPLICATION_LOG", "host":  "abc12345",  "timestamp": "1491377295213"}
     */
    @Override
    public EventLog deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = jp.getCodec().readTree(jp);
        EventLog eventLog = new EventLog();
        eventLog.setId(node.get("id").asText());
        final String state = node.get("state").asText();
        final long timestamp= node.get("timestamp").asLong();

        if (State.STARTED.toString().equals(state)){
            eventLog.setStart(timestamp);
        } else if (State.FINISHED.toString().equals(state)) {
            eventLog.setEnd(timestamp);
        }

        if(node.get("host") != null){
            eventLog.setHost(node.get("host").asText());
        }
        if(node.get("type") != null){
            eventLog.setType(node.get("type").asText());
        }
        return eventLog;
    }
}
