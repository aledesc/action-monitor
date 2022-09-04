package com.monitor.service.impl;

import ch.qos.logback.classic.Logger;
import com.monitor.db.AppRepository;
import com.monitor.service.AppService;
import com.monitor.util.AppStats;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppServiceImpl implements AppService {
    private static final Logger logger= (Logger) LoggerFactory.getLogger(AppServiceImpl.class);

    private AppRepository repository;

    public AppServiceImpl(AppRepository repository) {
        this.repository = repository;
    }

    public boolean save(AppStats app) {
        try {
            repository.deleteAll();
            repository.save(app);
            return true;
        }
        catch (Exception e) {
            logger.error("Error storing app stats: " + e.getMessage());
            return false;
        }
    }

    public Optional<AppStats> get() {
        return repository.findById(1);
    }

    public void clean() {
        repository.deleteAll();
    }
}
