package com.monitor.service;

import com.monitor.domain.Attendee;

import java.util.Set;

public interface AttendeeService {
    boolean save(Attendee attendee);
    Set<Attendee> get();
    void clean();
    void  delete(Attendee attendee);
    long count();
}
