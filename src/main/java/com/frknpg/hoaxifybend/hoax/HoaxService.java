package com.frknpg.hoaxifybend.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Hoax> getHoaxes(Pageable page) {
        return hoaxRepository.findAll(page);
    }
}
