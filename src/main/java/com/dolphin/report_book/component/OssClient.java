package com.dolphin.report_book.component;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

@Component
public class OssClient {

    @Value("${oss.bucket-name}")
    private String bucketName;

    @Value("${oss.end-point}")
    private String endPoint;

    @Value("${oss.access-key}")
    private String accessKeyId;

    @Value("${oss.access-secret}")
    private String accessKeySecret;

    /**
     * 上传文件
     */
    public String upLoad(MultipartFile file, String path) throws IOException {
        if (file == null || path == null) {
            return null;
        }
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        String extension = getFileExtension(file);
        // 设置文件路径
        String fileUrl = path + "/" + IdUtil.simpleUUID() + extension;
        String url = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file.getInputStream()));
        // 上传文件
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return url;
    }

    /**
     * 下载文件到输出流
     */
    public boolean download(String path, OutputStream outputStream) throws IOException {
        String objectName = path.split(String.format("https://%s.%s/", bucketName, endPoint))[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        IOUtils.copy(ossObject.getObjectContent(), outputStream);
        // 关闭 OSSClient
        ossClient.shutdown();
        return true;
    }

    /**
     * 删除文件
     */
    public boolean delete(String path) throws IOException {
        String objectName = path.split(String.format("https://%s.%s/", bucketName, endPoint))[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        ossClient.deleteObject(bucketName, objectName);
        // 关闭 OSSClient
        ossClient.shutdown();
        return true;
    }

    /**
     * 修改文件名
     */
    public boolean reName(String path) throws IOException {
        String objectName = path.split(String.format("https://%s.%s/", bucketName, endPoint))[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        // 关闭 OSSClient
        ossClient.shutdown();
        return true;
    }

    /**
     * 获取文件的扩展名
     */
    public String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        assert filename != null;
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 获取文件的大小描述
     */
    public String getFileSize(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
}

