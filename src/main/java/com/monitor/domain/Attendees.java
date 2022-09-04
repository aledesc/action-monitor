package com.monitor.domain;

import com.monitor.domain.Attendee;
import com.monitor.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Attendees {
    private Set<Attendee> attendees;
}
