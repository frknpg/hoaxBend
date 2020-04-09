package com.frknpg.hoaxifybend.user.vm;

import com.frknpg.hoaxifybend.user.User;
import lombok.Data;

@Data

public class UserVM {

    private String username;

    private String displayName;

    private String image;

    public UserVM(User user) {
        setUsername(user.getUsername());
        setDisplayName(user.getDisplayName());
        setImage(user.getImage());
    }
}
