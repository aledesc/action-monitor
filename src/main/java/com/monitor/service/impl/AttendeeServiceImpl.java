package com.monitor.service.impl;

import ch.qos.logback.classic.Logger;
import com.monitor.db.AttendeeRepository;
import com.monitor.domain.Attendee;
import com.monitor.service.AttendeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private static final Logger logger= (Logger) LoggerFactory.getLogger(AttendeeServiceImpl.class);

    private AttendeeRepository repository;

    public AttendeeServiceImpl(AttendeeRepository repository) {
        this.repository = repository;
    }

    public boolean save(Attendee attendee) {
        try {
            repository.save(attendee);
            return true;
        }
        catch (Exception e) {
            logger.error("Error storing message: " + e.getMessage());
            return false;
        }
    }

    public Set<Attendee> get() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    public void clean() {
        repository.deleteAll();
        logger.info("Deleted all entries for Attendees of the chat!");
    }
    public void delete(Attendee attendee) {
        repository.deleteById (attendee.getId());
        logger.info("Deleted Attendee " + attendee.getName() + " from chat!");
    }

    @Override
    public long count() {
        return repository.count();
    }
}
