package com.frknpg.hoaxifybend.file.vm;

import com.frknpg.hoaxifybend.file.FileAttachment;
import lombok.Data;

@Data
public class FileAttachmentVM {

    private String name;

    public FileAttachmentVM(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
    }

}
