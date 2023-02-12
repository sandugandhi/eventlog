package com.company.eventlog.repository;
import com.company.eventlog.domain.EventLog;
import org.springframework.data.repository.CrudRepository;

public interface EventLogRepository extends CrudRepository<EventLog,String>{
}
