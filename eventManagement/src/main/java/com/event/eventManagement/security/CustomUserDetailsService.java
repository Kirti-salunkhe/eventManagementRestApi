package com.event.eventManagement.security;

import com.event.eventManagement.entity.User;
import com.event.eventManagement.repository.UserRepository;
import com.event.eventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
       return org.springframework.security.core.userdetails.User.builder()
               .username(user.getEmail())
               .password(user.getPassword())
               .roles(user.getRoles().toArray(new String[0]))
               .build();
    }
}
