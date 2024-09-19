package com.learn.spring_boot_file_upload_download.service;

import com.learn.spring_boot_file_upload_download.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
