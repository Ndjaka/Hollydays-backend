package com.ozone.hollidays.services;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.ozone.hollidays.config.SecurityParams.DIRECTORY;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
   // public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    @Autowired
    MinIOService minIOService;

    public FileService(MinIOService minIOService) {
        this.minIOService = minIOService;
    }

    public List<String> uploadFile(List<MultipartFile> multipartFiles) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
       List<String> filenames = new ArrayList<>();
       for(MultipartFile file : multipartFiles) {

           String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//           Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
//           System.out.println("path: "+ fileStorage);
//           copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
           minIOService.WriteToMinIO(filename,file.getInputStream());
           filenames.add(filename);
       }

       return filenames;
   }


}
