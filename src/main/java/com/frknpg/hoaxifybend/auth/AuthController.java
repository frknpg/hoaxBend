package com.frknpg.hoaxifybend.auth;

import com.frknpg.hoaxifybend.shared.CurrentUser;
import com.frknpg.hoaxifybend.user.User;
import com.frknpg.hoaxifybend.user.UserRepository;
import com.frknpg.hoaxifybend.user.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/api/1.0/auth")
    public UserVM handleAuthentication(@CurrentUser User user) {
        return new UserVM(user);
    }

}
