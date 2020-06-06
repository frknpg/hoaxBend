package com.frknpg.hoaxifybend.user;

import com.frknpg.hoaxifybend.user.vm.UserUpdateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    void save(User user);

    Page<User> getUsers(Pageable page, User currentUser);

    User getByUsername(String username);

    User updateUser(String username, UserUpdateVM userUpdateVM);

    void delete(String username);
}
