package com.frknpg.hoaxifybend.configuration;

import com.frknpg.hoaxifybend.user.User;
import com.frknpg.hoaxifybend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User inDb = userRepository.findByUsername(s);
        if(inDb == null) {
            return (UserDetails) new UsernameNotFoundException("User not found");
        }
        return inDb;
    }
}
