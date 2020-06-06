package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "hoaxSecurity")
public class HoaxSecurityService {

    @Autowired
    HoaxRepository hoaxRepository;

    public boolean isAllowedToDelete(long id, User user) {
        Optional<Hoax> optionalHoax = hoaxRepository.findById(id);
        if (!optionalHoax.isPresent()) {
            return false;
        }

        Hoax inDb = optionalHoax.get();
        return inDb.getUser().getId() == user.getId();
    }

}
