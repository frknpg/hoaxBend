package com.frknpg.hoaxifybend.user.vm;

import com.frknpg.hoaxifybend.user.User;
import lombok.Data;

@Data
public class UserUpdateVM {

    private String displayName;

    public UserUpdateVM(User user) {
        setDisplayName(user.getDisplayName());
    }
}
