package com.frknpg.hoaxifybend.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHoaxService {

    void saveHoax(Hoax hoax);

    Page<Hoax> getHoaxes(Pageable page);
}
