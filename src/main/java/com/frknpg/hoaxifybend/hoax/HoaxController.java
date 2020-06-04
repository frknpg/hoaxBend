package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class HoaxController {

    @Autowired
    IHoaxService hoaxService;

    @PostMapping("/hoaxes")
    public GenericResponse saveHoax(@Valid @RequestBody Hoax hoax) {
        hoaxService.saveHoax(hoax);
        return new GenericResponse("Hoax is saved!");
    }

    @GetMapping("/hoaxes")
    public Page<Hoax> getHoaxes(Pageable page) {
        return hoaxService.getHoaxes(page);
    }
}
