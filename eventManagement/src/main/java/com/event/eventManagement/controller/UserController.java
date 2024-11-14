package com.event.eventManagement.controller;

import com.event.eventManagement.entity.Event;
import com.event.eventManagement.entity.User;
import com.event.eventManagement.repository.UserRepository;
import com.event.eventManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody User u){
        return userService.createUser(u);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/event/{eventId}")
    public Set<Event>updateEventByUserId(@PathVariable Long eventId){
       return userService.updateEventByUserId(eventId);
    }

    @GetMapping("/event")
    public Set<Event> getAllEventsByUser(){
        return userService.getAllUserEvents();
    }

    @DeleteMapping("/event/{eventId}")
    public void deleteEventByUserId(@PathVariable Long eventId){
        userService.deleteEventByUserById(eventId);
    }
}
