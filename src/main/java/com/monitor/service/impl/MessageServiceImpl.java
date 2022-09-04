package com.monitor.service.impl;

import ch.qos.logback.classic.Logger;
import com.monitor.db.MessageRepository;
import com.monitor.domain.Message;
import com.monitor.service.MessageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger= (Logger) LoggerFactory.getLogger(MessageServiceImpl.class);

    private MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    public boolean save(Message message) {
        try {
            repository.save(message);
            return true;
        }
        catch (Exception e)
        {
            logger.error("Error storing message: " + e.getMessage());
            return false;
        }
    }

    public Set<Message> get() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void clean() {
        repository.deleteAll();
    }

}
