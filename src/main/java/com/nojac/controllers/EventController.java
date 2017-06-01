package com.nojac.controllers;

import com.nojac.errors.BadDateFormatException;
import com.nojac.models.Attendant;
import com.nojac.models.Calendar;
import com.nojac.models.Event;
import com.nojac.models.NjUser;
import com.nojac.repositories.EventRepository;
import com.nojac.repositories.NjUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nickolas on 5/30/17.
 */
@RestController
public class EventController {

    private long beginTime;
    private long endTime;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NjUserRepository njUserRepository;

    @RequestMapping(value="/allevents", method=RequestMethod.GET)
    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) {
        Event created = eventRepository.save(event);
        return created;
    }

    @RequestMapping(value="/updateevent", method=RequestMethod.PATCH)
    public Event updateEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @RequestMapping(value="/removeevent", method=RequestMethod.DELETE)
    public Event removeEvent(@RequestBody Event event) {
        eventRepository.delete(event);
        return event;
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        return eventRepository.findByDatesBetween(startDate, endDate);
    }

    @RequestMapping(value="/specialevent", method=RequestMethod.POST)
    public Event addEventWithoutDoubleBooking(@RequestBody Event event) {
        Long userId = event.getCalendar().getNjUser().getUserId();
//        System.out.println("userId: " + userId);
        Set<Event> events = getEventsByUserId(userId);
        boolean search = false;
        //retrieve start, end
        for (Event Ev : events) {
            if(!(event.getEnd().before(Ev.getStart()) || event.getStart().after(Ev.getEnd()))){
                search = true;
                break;
            }
        }
        if(!search){
            eventRepository.save(event);
//            return false;
            return event;
        }else{
            Event error = new Event();
            error.setDescription("WARNING");
            return error;
        }
    }

    private Set<Event> getEventsByUserId(Long userId) {
        NjUser njUser = njUserRepository.findOne(userId);
        Set<Event> events = njUser.getCalendar().getEvents();
        return events;
    }


    public Set<Date> findAvailability(Event event,String D1,String D2){
        //gather all calendars associated with this event
        Calendar ownerCalendar = event.getCalendar();
        Set<Attendant> attendants = event.getAttendants();
        //the available dates
        Set<Date> rDates = new HashSet<>();

        Calendar temp = new Calendar();
        for(Attendant att: attendants) {
            for (Event ev : att.getEvents()) {
                temp.getEvents().add(ev);
            }
        }
        for (Event ev : ownerCalendar.getEvents()){
            temp.getEvents().add(ev);
        }

        //the time is set by the organizer
        setUp(D1,D2);
        //creating datetimes..
        for(int i=1;i<250;i++) rDates.add(new Date(getRandomTimeBetweenTwoDates()));
        //check which dates are valid

        for(Event ev: temp.getEvents()){
            for(Date d: rDates) {
                if ((d.before(ev.getEnd()) &&  d.after(ev.getStart())) || d.equals(ev.getEnd()) || d.equals(ev.getStart()))
                   rDates.remove(d);
            }
        }
        return rDates;
    }


    private void setUp (String D1 ,String D2) {
        //D1 = "2013-01-01 00:00:00"
        //D2 = "2013-12-31 00:58:00"
        beginTime = Timestamp.valueOf(D1).getTime();
        endTime = Timestamp.valueOf(D2).getTime();
    }

    private long getRandomTimeBetweenTwoDates () {
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

}
