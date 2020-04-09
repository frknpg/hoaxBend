package com.frknpg.hoaxifybend.user;

import com.frknpg.hoaxifybend.shared.GenericResponse;
import com.frknpg.hoaxifybend.user.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @PostMapping("/api/1.0/users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("User Create Success");
    }

    @GetMapping("/api/1.0/users")
    Page<UserVM> getUsers(Pageable page) {
        return userService.getUsers(page).map(UserVM::new);
    }

    //ERROR HANDLER KULLANDIIGMIZ ICIN GEREK KALMADI
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
//        ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
//        Map<String, String> validationErrors = new HashMap<>();
//        for(FieldError fieldError :  exception.getBindingResult().getFieldErrors()) {
//            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        error.setValidationErrors(validationErrors);
//        return error;
//    }
}
