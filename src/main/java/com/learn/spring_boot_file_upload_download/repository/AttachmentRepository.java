package com.learn.spring_boot_file_upload_download.repository;

import com.learn.spring_boot_file_upload_download.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
