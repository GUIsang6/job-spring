package cn.acdog.service.impl;

import cn.acdog.common.QiniuConfig;
import cn.acdog.service.FileUploadService;
import cn.acdog.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private QiniuConfig qiniuConfig;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String path;
    Auth auth;
    Configuration configuration;
    UploadManager uploadManager;

    @PostConstruct
    public void init(){
        accessKey = qiniuConfig.getAccessKey();
        secretKey = qiniuConfig.getSecretKey();
        bucket = qiniuConfig.getBucket();
        path = qiniuConfig.getPath();
        auth = Auth.create(accessKey,secretKey);
        configuration = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(configuration);
    }

    private String getUpToken(){
        return auth.uploadToken(bucket);
    }
    @Override
    public String uploadImg(MultipartFile file) {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return path + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                log.info("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
