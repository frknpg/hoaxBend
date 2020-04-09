package com.frknpg.hoaxifybend.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    public void save(User user);

    public Page<UserProjection> getUsers(Pageable page);
}
