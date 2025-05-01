package com.avalant.quiz.controller;

import com.avalant.quiz.entity.Application;
import com.avalant.quiz.entity.Attachment;
import com.avalant.quiz.repository.AttachmentRepository;
import com.avalant.quiz.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final AttachmentRepository attachmentRepository;
    private final ApplicationService applicationService;
    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("applicationId") String applicationId
    ) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String filename = System.currentTimeMillis() + "_" + originalFilename;
        Path targetPath = Paths.get(uploadDir, filename);

        Files.createDirectories(targetPath.getParent());
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = new Attachment();
        attachment.setAttachmentId(UUID.randomUUID().toString());
        Application application = applicationService.getApplicationById(applicationId);
        attachment.setApplication(application);
        attachment.setFileName(filename);
        attachment.setRefId(targetPath.toString());

        attachmentRepository.save(attachment);

        return ResponseEntity.ok("File uploaded successfully");
    }
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Path path = Paths.get("uploads").resolve(filename).normalize();
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Detect file type (e.g., image/jpeg, image/png)
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}