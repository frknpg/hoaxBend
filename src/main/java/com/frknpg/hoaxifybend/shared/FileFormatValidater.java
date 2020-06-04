package com.frknpg.hoaxifybend.shared;

import com.frknpg.hoaxifybend.file.FileService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileFormatValidater implements ConstraintValidator<CheckFileFormat, String> {

    @Autowired
    FileService fileService;

    String[] types;

    @Override
    public void initialize(CheckFileFormat constraintAnnotation) {
        this.types = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        String fileType = fileService.detectType(s);

        for (String supportedType : this.types) {
            if (fileType.equalsIgnoreCase(supportedType)) {
                return true;
            }
        }

        String supporetedTypes = Arrays.stream(this.types).collect(Collectors.joining(","));

        constraintValidatorContext.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext.addMessageParameter("types", supporetedTypes);
        hibernateConstraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate()).addConstraintViolation();

        return false;
    }
}
