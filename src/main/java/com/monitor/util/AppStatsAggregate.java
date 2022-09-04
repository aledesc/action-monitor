package com.monitor.util;

import com.monitor.domain.Attendee;
import com.monitor.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Getter
public class AppStatsAggregate
{
    private Optional<AppStats> appStats;
    private Set<Attendee> attendees;
    private Set<Message> messages;

    private String getAttendeesStr()
    {
        StringBuilder sb= new StringBuilder( Constants.HR + Constants.ATTENDEES + Constants.BR);
        attendees.stream().forEach(a -> sb.append( a.getName() + Constants.BR));

        return sb.toString();
    }

    private String getMessagesStr()
    {
        StringBuilder sb= new StringBuilder( Constants.HR + Constants.MESSAGES + Constants.BR);
        messages.stream().forEach(m -> sb.append(m.getTime() + Constants.NBSP + m.getSender() + Constants.NBSP + m.getText() + Constants.BR));

        return sb.toString();
    }

    @Override
    public String toString()
    {

        StringBuilder sb= new StringBuilder( appStats.isPresent() ? appStats.get().toString() : Constants.EMPTY );
        return sb.append( getAttendeesStr() ).append( getMessagesStr() ).toString();
    }
}
