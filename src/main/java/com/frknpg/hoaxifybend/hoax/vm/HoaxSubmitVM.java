package com.frknpg.hoaxifybend.hoax.vm;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class HoaxSubmitVM {

    @Size(min = 1, max = 300)
    private String content;

    private long attachmentId;
}
