package com.example.DartsInventory.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // get file name
        String filename = file.getOriginalFilename();

        // get file path
        String filepath = path + File.separator + filename;

        // create file object

        File file1 = new File(path);
        if(!file1.exists()) {
            file1.mkdir();
        }

        // copy file or upload
        Files.copy(file.getInputStream(), Paths.get(filepath));

        return filename;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);
    }
}
