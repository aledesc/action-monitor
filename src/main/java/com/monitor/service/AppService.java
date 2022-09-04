package com.monitor.service;

import com.monitor.util.AppStats;

import java.util.Optional;

public interface AppService {

    boolean save(AppStats app);
    Optional<AppStats> get();
    void clean();
}
