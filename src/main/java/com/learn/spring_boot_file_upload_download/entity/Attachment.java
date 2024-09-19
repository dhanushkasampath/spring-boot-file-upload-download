package com.learn.spring_boot_file_upload_download.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String fileName;
    private String fileType;

    @Lob //we use this annotation since we need to store data as blob type
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.id = String.valueOf(UUID.randomUUID());
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
