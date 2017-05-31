package com.nojac.repositories;

import com.nojac.models.Event;
import com.nojac.models.NjUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by nickolas on 5/30/17.
 */
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
    Event save(Event event);
    void delete(Event event);

    @Query("select b from Event b " +
            "where b.start between ?1 and ?2 and b.end between ?1 and ?2")
    List<Event> findByDatesBetween(Date start, Date end);

//    List<Event> getEventsByUserId(Long userId);
}
