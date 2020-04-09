package com.frknpg.hoaxifybend.user;

import com.frknpg.hoaxifybend.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void save(User user) {
        String encrytedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encrytedPassword);
        userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Pageable page, User currentUser) {
        if(currentUser != null) {
            return userRepository.findByUsernameNot(currentUser.getUsername(), page);
        }
        return userRepository.findAll(page);
    }

    @Override
    public User getByUsername(String username) {
        User inDb = userRepository.findByUsername(username);
        if(inDb == null){
            throw new NotFoundException();
        }
        return inDb;
    }
}
