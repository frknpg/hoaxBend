package com.frknpg.hoaxifybend.hoax;

import org.springframework.stereotype.Service;

@Service
public class HoaxService implements IHoaxService {

    HoaxRepository hoaxRepository;

    public HoaxService(HoaxRepository hoaxRepository) {
        this.hoaxRepository = hoaxRepository;
    }

    @Override
    public void saveHoax(Hoax hoax) {
        hoaxRepository.save(hoax);
    }
}
