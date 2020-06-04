package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
