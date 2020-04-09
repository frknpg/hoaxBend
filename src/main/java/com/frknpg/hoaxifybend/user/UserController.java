package com.frknpg.hoaxifybend.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.frknpg.hoaxifybend.shared.GenericResponse;
import com.frknpg.hoaxifybend.shared.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @JsonView(value = Views.Base.class)
    Page<User> getUsers(Pageable page) {
        return userService.getUsers(page);
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
