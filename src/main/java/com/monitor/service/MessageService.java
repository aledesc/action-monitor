package com.monitor.service;

import com.monitor.domain.Message;

import java.util.Set;

public interface MessageService {
    boolean save(Message message);
    Set<Message> get();
    void clean();
}
