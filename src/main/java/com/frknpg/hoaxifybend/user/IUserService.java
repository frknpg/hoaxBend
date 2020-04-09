package com.frknpg.hoaxifybend.user;

import com.frknpg.hoaxifybend.user.vm.UserUpdateVM;
import com.frknpg.hoaxifybend.user.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    public void save(User user);

    public Page<User> getUsers(Pageable page, User currentUser);

    public User getByUsername(String username);

    public User updateUser(String username, UserUpdateVM userUpdateVM);
}
