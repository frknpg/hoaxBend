package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.file.FileAttachment;
import com.frknpg.hoaxifybend.file.FileAttachmentRepository;
import com.frknpg.hoaxifybend.file.FileService;
import com.frknpg.hoaxifybend.hoax.vm.HoaxSubmitVM;
import com.frknpg.hoaxifybend.user.User;
import com.frknpg.hoaxifybend.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaxService implements IHoaxService {

    HoaxRepository hoaxRepository;
    UserService userService;
    FileAttachmentRepository fileAttachmentRepository;
    FileService fileService;

    public HoaxService(HoaxRepository hoaxRepository, UserService userService,
                       FileAttachmentRepository fileAttachmentRepository,
                       FileService fileService) {
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
    }

    @Override
    public void saveHoax(HoaxSubmitVM hoaxSubmitVM, User user) {
        Hoax hoax = new Hoax();
        hoax.setContent(hoaxSubmitVM.getContent());
        hoax.setUser(user);
        hoaxRepository.save(hoax);

        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(hoaxSubmitVM.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setHoax(hoax);
            fileAttachmentRepository.save(fileAttachment);
        }
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

    @Override
    public void delete(long id) {
        Hoax inDb = hoaxRepository.getOne(id);
        if (inDb.getFileAttachment() != null) {
            String fileName = inDb.getFileAttachment().getName();
            fileService.deleteAttachmentImage(fileName);
        }
        hoaxRepository.deleteById(id);
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
