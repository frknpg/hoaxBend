package com.frknpg.hoaxifybend.user;

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
    public Page<UserProjection> getUsers(Pageable page) {
        return userRepository.getAllUsersProjection(page);
    }
}
