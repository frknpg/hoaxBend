package com.frknpg.hoaxifybend.user;

import com.frknpg.hoaxifybend.error.NotFoundException;
import com.frknpg.hoaxifybend.file.FileService;
import com.frknpg.hoaxifybend.user.vm.UserUpdateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    FileService fileService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService) {

        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.fileService = fileService;
    }

    @Override
    public void save(User user) {
        String encrytedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encrytedPassword);
        userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Pageable page, User currentUser) {
        if(currentUser != null) {
            return userRepository.findByUsernameNot(currentUser.getUsername(), page);
        }
        return userRepository.findAll(page);
    }

    @Override
    public User getByUsername(String username) {
        User inDb = userRepository.findByUsername(username);
        if(inDb == null){
            throw new NotFoundException();
        }
        return inDb;
    }

    @Override
    public User updateUser(String username, UserUpdateVM userUpdateVM) {
        User inDb = getByUsername(username);
        inDb.setDisplayName(userUpdateVM.getDisplayName());
        if (userUpdateVM.getImage() != null) {
            String oldImageName = inDb.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(userUpdateVM.getImage());
                inDb.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleteImage(oldImageName);
        }
        return userRepository.save(inDb);
    }

}
