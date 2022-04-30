package com.ozone.hollidays.controllers;

import com.ozone.hollidays.services.FileService;
import com.ozone.hollidays.services.MinIOService;
import io.minio.errors.MinioException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController()
@RequestMapping("api/v1/file")
public class FileController {

    private final FileService fileService;
    private final MinIOService minIOService;

    public FileController(FileService fileService, MinIOService minIOService) {
        this.fileService = fileService;
        this.minIOService = minIOService;
    }


    @PostMapping("/upload")
    public ResponseEntity<List<String>> upload(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        return ResponseEntity.ok().body(fileService.uploadFile(multipartFiles));
    }

    @GetMapping("/{object}")
    public String getObject(@PathVariable("object") String object) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        String url = minIOService.getUrl(object);
        return url;
    }


}
