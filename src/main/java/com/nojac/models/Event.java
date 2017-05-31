package com.nojac.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nickolas on 5/30/17.
 */
@Entity
@Table(name = "Event")
public class Event {

    //TODO add fields

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(name = "event_id", insertable = false)
    private Long id;

    private String title;
    private String description;
    private Date start;
    private Date end;

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "calendar_id", foreignKey = @ForeignKey(name = "fk_calendar"))
    @JsonIgnoreProperties("njEvents")
    //    @JsonManagedReference
    private Calendar calendar;

    @ManyToMany
    @JoinTable(name="event_attendant", joinColumns=@JoinColumn(name="event_id"), inverseJoinColumns=@JoinColumn(name="attendant_id"))
    @JsonIgnoreProperties("events")
    private Set<Attendant> attendants = new HashSet<>(0);

    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    public Set<Attendant> getAttendants() {
        return attendants;
    }
    public void setAttendants(Set<Attendant> attendants) {
        this.attendants = attendants;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public Event(Long id, String title, String description, Date start, Date end) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }
    public Event() {
        super();
    }
    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", description="
                + description + ", start=" + start + ", end=" + end + "]";
    }
}
