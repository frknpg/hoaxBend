package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHoaxService {

    void saveHoax(Hoax hoax, User user);

    Page<Hoax> getHoaxes(Pageable page);

    Page<Hoax> getHoaxesOfUser(Pageable page, String username);

    Page<Hoax> getOldHoaxes(long id, Pageable page);

    Page<Hoax> getOldHoaxesOfUser(long id, Pageable page, String username);

    long getNewHoaxesCount(long id);

    long getUserNewHoaxesCount(long id, String username);
}
