package com.monitor.domain;

import com.monitor.util.Constants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "messages")
public class Message {

    @Id
    private String id;

    @Version
    private Integer version;

    private String sender;
    private String text;
    private Date time;

    public Message()
    {
        this.id= UUID.randomUUID().toString();
        this.time= new Date();
    }
    public Message(String sender, String text)
    {
        this();
        this.sender= sender;
        this.text= text;
    }
    public Message(String id, Integer version, String sender, String text)
    {
        this(sender,text);
        this.version= version;
    }

    @Override
    public String toString(){

        String _strTime = (new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN)).format(time); // formats to 09/23/2009 13:53:28.238
        return _strTime + "- " + sender + ": " + text; }
}
