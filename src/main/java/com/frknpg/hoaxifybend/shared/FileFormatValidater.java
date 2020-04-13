package com.frknpg.hoaxifybend.shared;

import com.frknpg.hoaxifybend.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileFormatValidater implements ConstraintValidator<CheckFileFormat, String> {

    @Autowired
    FileService fileService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        String fileType = fileService.detectType(s);
        return fileType.equalsIgnoreCase("image/jpeg") || fileType.equalsIgnoreCase("image/png");
    }
}
