package com.event.eventManagement.service;

import com.event.eventManagement.entity.Event;
import com.event.eventManagement.entity.User;

import java.util.Set;

public interface UserService {
    User createUser(User u);

    User getUserById(Long id);

    Set<Event> updateEventByUserId(Long eventId);

    Set<Event> getAllUserEvents();

    void deleteEventByUserById(Long eventId);
}
