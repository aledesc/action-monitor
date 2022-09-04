package com.monitor.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Getter
@Setter

@Entity
@Table(name = "app")
public class AppStats {

    @Id
    private Integer id;

    @Version
    private Integer version;

    private String appVersion="1.0-RC";
    private String startedAt;

    public AppStats()
    {
        this.id= 1;
        this.startedAt= (new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN)).format(new Date());
    }

    public AppStats(Integer id, Integer version) throws IllegalArgumentException
    {
        this();
        this.version= version;
        this.startedAt= (new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN)).format(new Date());
    }

    public AppStats(Integer id, Integer version, String appVersion) throws IllegalArgumentException
    {
        this(id,version);
        this.appVersion= appVersion;
    }

    @Override
    public String toString()
    {
        StringBuilder sb= new StringBuilder();
        sb.append( Constants.STARTED + startedAt + Constants.BR);
        sb.append( Constants.VERSION + appVersion );

        return sb.toString();
    }
}
