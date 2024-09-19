package com.learn.spring_boot_file_upload_download.controller;

import com.learn.spring_boot_file_upload_download.entity.Attachment;
import com.learn.spring_boot_file_upload_download.model.ResponseData;
import com.learn.spring_boot_file_upload_download.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    // we should use the word "file" when making the post call.
    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception { // we need to convert this file to a byte[] and save to a database
        Attachment attachment = attachmentService.saveAttachment(file);
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(attachment.getId())
            .toUriString();
        return new ResponseData(attachment.getFileName(),
            downloadUrl,
            file.getContentType(),
            file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType())) // content type is what ever the content type of the file itself
                .header(HttpHeaders.CONTENT_DISPOSITION, // this means content will be downloaded
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData())); //we return body as a  byte array
    }
}


// 