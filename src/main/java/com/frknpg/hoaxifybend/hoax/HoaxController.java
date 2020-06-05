package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.hoax.vm.HoaxVM;
import com.frknpg.hoaxifybend.shared.CurrentUser;
import com.frknpg.hoaxifybend.shared.GenericResponse;
import com.frknpg.hoaxifybend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1.0")
public class HoaxController {

    @Autowired
    IHoaxService hoaxService;

    @PostMapping("/hoaxes")
    public GenericResponse saveHoax(@Valid @RequestBody Hoax hoax, @CurrentUser User user) {
        hoaxService.saveHoax(hoax, user);
        return new GenericResponse("Hoax is saved!");
    }

    @GetMapping("/hoaxes")
    public Page<HoaxVM> getHoaxes(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return hoaxService.getHoaxes(page).map(HoaxVM::new);
    }

    @GetMapping("/hoaxes/{id:[0-9]+}")
    public ResponseEntity<?> getHoaxesRelative(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                               @PathVariable long id,
                                               @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
                                               @RequestParam(name = "direction", defaultValue = "before") String direction) {
        if (count) {
            long newHoaxCount = hoaxService.getNewHoaxesCount(id);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newHoaxCount);
            return ResponseEntity.ok(response);
        }
        if(direction.equals("after")) {
            List<HoaxVM> newHoaxes = hoaxService.getNewHoaxes(id, page.getSort())
                    .stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }

        return ResponseEntity.ok(hoaxService.getOldHoaxes(id, page).map(HoaxVM::new));
    }

    @GetMapping("/users/{username}/hoaxes")
    public Page<HoaxVM> getUserHoaxes(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page, @PathVariable String username) {
        return hoaxService.getHoaxesOfUser(page, username).map(HoaxVM::new);
    }

    @GetMapping("/users/{username}/hoaxes/{id:[0-9]+}")
    public ResponseEntity<?> getUserHoaxesRelative(@PathVariable long id,
                                                   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page,
                                                   @PathVariable String username,
                                                   @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
                                                   @RequestParam(name = "direction", defaultValue = "before") String direction) {
        if (count) {
            long newHoaxCount = hoaxService.getUserNewHoaxesCount(id, username);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newHoaxCount);
            return ResponseEntity.ok(response);
        }
        if(direction.equals("after")) {
            List<HoaxVM> newHoaxes = hoaxService.getUserNewHoaxes(id, page.getSort(), username)
                    .stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }

        return ResponseEntity.ok(hoaxService.getOldHoaxesOfUser(id, page, username).map(HoaxVM::new));
    }
}
