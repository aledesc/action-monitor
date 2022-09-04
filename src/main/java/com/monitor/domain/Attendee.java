package com.monitor.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    private Integer id;

    @Version
    private Integer version;

    @Column(unique=true)
    private String name;

    public Attendee()
    {
        Random random= new Random(Calendar.getInstance().getTimeInMillis());
        this.id= random.nextInt();
    }

    public Attendee(Integer id) throws IllegalArgumentException
    {
        this.id= id;
    }

    public Attendee(String name)
    {
        this();
        this.name= name;
    }

    public Attendee(Integer id, String name)
    {
        this(id);
        this.name= name;
    }
    public Attendee(Integer id, Integer version, String name)
    {
        this(id);
        this.version= version;
        this.name= name;
    }
}
