package com.frknpg.hoaxifybend.file;

import com.frknpg.hoaxifybend.configuration.AppConfiguration;
import com.frknpg.hoaxifybend.user.User;
import org.apache.tika.Tika;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
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
        File target = new File(appConfiguration.getProfileStoragePath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);

        byte[] base64encoded = Base64.getDecoder().decode(image);

        outputStream.write(base64encoded);
        outputStream.close();
        return fileName;
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteProfileImage(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(oldImageName, appConfiguration.getProfileStoragePath());
    }

    public void deleteAttachmentImage(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(oldImageName, appConfiguration.getAttachmentStoragePath());
    }

    public void deleteFile(String oldImageName, String path) {
        try {
            Files.delete(Paths.get(path, oldImageName));
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
        File target = new File(appConfiguration.getAttachmentStoragePath() + "/" + fileName);
        String fileType = null;
        try {
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
            fileType = tika.detect(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileAttachment attachment = new FileAttachment();
        attachment.setName(fileName);
        attachment.setFileType(fileType);
        return fileAttachmentRepository.save(attachment);
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void cleanUpStorage() {
        Date twentyFourHourAgo = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000));
        List<FileAttachment> filesToBeDeleted = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(twentyFourHourAgo);

        for (FileAttachment file : filesToBeDeleted) {
            deleteAttachmentImage(file.getName());
            fileAttachmentRepository.deleteById(file.getId());
        }
    }

    public void deleteAllFilesForUser(User inDb) {
        deleteProfileImage(inDb.getImage());
        List<FileAttachment> filesToBeRemoved = fileAttachmentRepository.findByHoaxUser(inDb);
        for (FileAttachment file : filesToBeRemoved) {
            deleteAttachmentImage(file.getName());
        }
    }
}