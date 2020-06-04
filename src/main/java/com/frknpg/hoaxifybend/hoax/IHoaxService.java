package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.channels.FileChannel;

public interface IHoaxService {

    void saveHoax(Hoax hoax, User user);

    Page<Hoax> getHoaxes(Pageable page);

    Page<Hoax> getHoaxesOfUser(Pageable page, String username);
}
