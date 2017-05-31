package com.nojac.controllers;

import com.nojac.errors.BadDateFormatException;
import com.nojac.models.Event;
import com.nojac.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nickolas on 5/30/17.
 */
@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value="/allevents", method=RequestMethod.GET)
    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) {
        Event created = eventRepository.save(event);
        return created;
    }

    @RequestMapping(value="/specialevent", method=RequestMethod.POST)
    public boolean addEventWithoutDoubleBooking(@RequestBody Event event) {
        List<Event> events = getEventsByUserId();
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
            return false;
        }else{
            return true;
        }
    }

    public List<Event> getEventsByUserId() {
        return new ArrayList<Event>();
    }


    @RequestMapping(value="/event", method=RequestMethod.PATCH)
    public Event updateEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @RequestMapping(value="/event", method=RequestMethod.DELETE)
    public void removeEvent(@RequestBody Event event) {
        eventRepository.delete(event);
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

}
