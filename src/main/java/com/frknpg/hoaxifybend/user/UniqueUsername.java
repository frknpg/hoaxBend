package com.frknpg.hoaxifybend.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidater.class })
public @interface UniqueUsername {

    String message() default "{hoaxify.constraints.username.Unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
