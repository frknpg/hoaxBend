package com.frknpg.hoaxifybend.shared;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileFormatValidater.class})
public @interface CheckFileFormat {

    String message() default "{hoaxify.constraints.file.type.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] types();
}
