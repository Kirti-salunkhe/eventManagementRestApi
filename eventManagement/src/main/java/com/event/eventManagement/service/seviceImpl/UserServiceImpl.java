package com.event.eventManagement.service.seviceImpl;

import com.event.eventManagement.entity.Event;
import com.event.eventManagement.entity.User;
import com.event.eventManagement.repository.UserRepository;
import com.event.eventManagement.service.EventService;
import com.event.eventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventService eventService;

    public  User getLoggedInUser(){
      Authentication auth= SecurityContextHolder.getContext().getAuthentication();
      String email= auth.getName();
      return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public User createUser(User u) {
        if(userRepository.existsByEmail(u.getEmail())){
            throw new RuntimeException("Email already exist");
        }
        u.setRoles(Arrays.asList("USER"));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public Set<Event> updateEventByUserId(Long eventId) {
        Set<Event> allEvents;
        User u = getLoggedInUser();
        Event e=eventService.getEventById(eventId);
        allEvents=u.getEvents();
        allEvents.add(e);
        u.setEvents(allEvents);
        userRepository.save(u);
        return u.getEvents();
    }

    @Override
    public Set<Event> getAllUserEvents() {
        User u = getLoggedInUser();
        return u.getEvents();
    }

    @Override
    public void deleteEventByUserById( Long eventId) {
        Set<Event> allEvents;
        User u=getLoggedInUser();
        allEvents=u.getEvents();
       Set<Event>events= allEvents.stream().filter(e->e.getId()!=eventId).collect(Collectors.toSet());
       u.setEvents(events);
       userRepository.save(u);
    }

}
