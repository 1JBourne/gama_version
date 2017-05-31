package com.nojac.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nickolas on 5/23/17.
 */
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @Column(name = "calendar_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long calendarId;

//    @Column(name = "calendar_name")
    private String calendarName;

//    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "calendar")
    @JsonIgnoreProperties("calendar")
//    @JsonBackReference
    private Set<Event> events= new HashSet<>(
            0);

    @OneToOne(mappedBy = "calendar")
    @JsonIgnoreProperties("calendar")
    private NjUser njUser;

    public NjUser getNjUser() {
        return njUser;
    }

    public void setNjUser(NjUser njUser) {
        this.njUser = njUser;
    }

    public Calendar() {
        //def ctr
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarId=" + calendarId +
                ", calendarName='" + calendarName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
