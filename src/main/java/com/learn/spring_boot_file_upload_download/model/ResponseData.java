package com.learn.spring_boot_file_upload_download.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
