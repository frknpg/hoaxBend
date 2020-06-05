package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import com.frknpg.hoaxifybend.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HoaxService implements IHoaxService {

    HoaxRepository hoaxRepository;
    UserService userService;

    public HoaxService(HoaxRepository hoaxRepository, UserService userService) {
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
    }

    @Override
    public void saveHoax(Hoax hoax, User user) {
        hoax.setUser(user);
        hoaxRepository.save(hoax);
    }

    @Override
    public Page<Hoax> getHoaxes(Pageable page) {
        return hoaxRepository.findAll(page);
    }

    @Override
    public Page<Hoax> getHoaxesOfUser(Pageable page, String username) {
        User inDb = userService.getByUsername(username);
        return hoaxRepository.findByUser(inDb, page);
    }

    @Override
    public Page<Hoax> getOldHoaxes(long id, Pageable page) {
        return hoaxRepository.findByIdLessThan(id, page);
    }

    @Override
    public Page<Hoax> getOldHoaxesOfUser(long id, Pageable page, String username) {
        User inDb = userService.getByUsername(username);
        return hoaxRepository.findByIdLessThanAndUser(id, inDb, page);
    }
}
