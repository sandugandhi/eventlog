package com.company.eventlog.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "eventslog")
public class EventLog {
    @Id
    private String id;
    private long start;
    private long end;
    private long duration;
    private boolean flag = false;
    private String type;
    private String host;

    public void merge(EventLog eventLog) {
        this.id=eventLog.id;
        this.host=(eventLog.getHost() != null && !eventLog.getHost().isEmpty())? eventLog.getHost():"";
        this.type=(eventLog.getType() != null && !eventLog.getType().isEmpty())? eventLog.getType():"";
        if(eventLog.getStart() > 0L ) { this.start=eventLog.getStart();}
        if(eventLog.getEnd() > 0L )  { this.end = eventLog.getEnd();}

    }


}
