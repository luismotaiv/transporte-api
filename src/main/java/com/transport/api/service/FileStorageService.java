package com.transport.api.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String filePath = Paths.get(UPLOAD_DIR, fileName)
                    .toString()
                    .replace("\\", "/");

            Path path = Paths.get(filePath);

            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }
    }
}