package com.frknpg.hoaxifybend.user.vm;

import com.frknpg.hoaxifybend.shared.CheckFileFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateVM {

    @NotNull
    @Size(min = 4, max = 255)
    private String displayName;

    @CheckFileFormat(types = {"image/png", "image/jpeg"})
    private String image;

}
