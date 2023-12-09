package cn.acdog.controller;

import cn.acdog.pojo.Result;
import cn.acdog.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
//    @PostMapping("/upload")
//    public Result<String> upload(MultipartFile file) throws IOException {
////        String originalFilename = file.getOriginalFilename();
////        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
////        file.transferTo(new File(("A:\\Desktop\\files\\")+fileName));
////        return Result.success("http://localhost:8080/files/"+originalFilename);
//    }
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("七牛云上传：{}",file.getOriginalFilename());
        if ((file.isEmpty()))
        {
            return Result.error("文件为空");
        }
        String fileUrl = fileUploadService.uploadImg(file);
        if(fileUrl==null)
        {
            return Result.error("上传失败");
        }
        return Result.success(fileUrl);
    }
}
