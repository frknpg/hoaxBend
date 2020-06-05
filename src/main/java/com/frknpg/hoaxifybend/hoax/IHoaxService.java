package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IHoaxService {

    void saveHoax(Hoax hoax, User user);

    Page<Hoax> getHoaxes(Pageable page);

    Page<Hoax> getHoaxesOfUser(Pageable page, String username);

    Page<Hoax> getOldHoaxes(long id, String username, Pageable page);

    long getNewHoaxesCount(long id, String username);

    List<Hoax> getNewHoaxes(long id, String username, Sort sort);

}
