package com.frknpg.hoaxifybend.file;

import com.frknpg.hoaxifybend.configuration.AppConfiguration;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    AppConfiguration appConfiguration;
    Tika tika;
    FileAttachmentRepository fileAttachmentRepository;

    public FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
        this.fileAttachmentRepository = fileAttachmentRepository;
    }


    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);

        byte[] base64encoded = Base64.getDecoder().decode(image);

        outputStream.write(base64encoded);
        outputStream.close();
        return fileName;
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteImage(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        try {
            Files.delete(Paths.get(appConfiguration.getUploadPath(), oldImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String detectType(String image) {
        byte[] base64encoded = Base64.getDecoder().decode(image);
        return tika.detect(base64encoded);
    }

    public FileAttachment saveHoaxAttachment(MultipartFile multipartFile) {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
        try {
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileAttachment attachment = new FileAttachment();
        attachment.setName(fileName);
        return fileAttachmentRepository.save(attachment);
    }
}