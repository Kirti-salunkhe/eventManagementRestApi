package com.event.eventManagement.service;


import com.event.eventManagement.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Page<Event> getAllEvent(Pageable page);
    Event getEventById(Long id);
    Event updateEvent(Event event,Long id);
    void deleteEvent(long id);
}
