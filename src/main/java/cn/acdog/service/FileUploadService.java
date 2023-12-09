package cn.acdog.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadImg(MultipartFile file);
}
