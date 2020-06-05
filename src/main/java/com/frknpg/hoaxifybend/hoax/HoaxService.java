package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import com.frknpg.hoaxifybend.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Hoax> getOldHoaxes(long id, String username, Pageable page) {
        Specification<Hoax> spec = idLessThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            spec = spec.and(userIs(inDb));
        }
        return hoaxRepository.findAll(spec, page);
    }

    @Override
    public long getNewHoaxesCount(long id, String username) {
        Specification<Hoax> spec = idGreaterThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            spec = spec.and(userIs(inDb));
        }
        return hoaxRepository.count(spec);
    }

    @Override
    public List<Hoax> getNewHoaxes(long id, String username, Sort sort) {
        Specification<Hoax> spec = idGreaterThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            spec = spec.and(userIs(inDb));
        }
        return hoaxRepository.findAll(spec, sort);
    }

    Specification<Hoax> idLessThan(long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("id"), id);
    }

    Specification<Hoax> idGreaterThan(long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"), id);
    }

    Specification<Hoax> userIs(User user) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("user"), user);
    }

}
