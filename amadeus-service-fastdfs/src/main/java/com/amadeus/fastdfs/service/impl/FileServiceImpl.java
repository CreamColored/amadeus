package com.amadeus.fastdfs.service.impl;

import com.amadeus.fastdfs.client.CourseClient;
import com.amadeus.fastdfs.service.FileService;
import com.amadeus.fastdfs.utils.CommonFileUtil;
import com.amadeus.framework.domain.course.CourseImage;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.FdfsResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private CommonFileUtil commonFileUtil;

    @Autowired
    private CourseClient courseClient;

    @Override
    public FdfsResult fileUpload(MultipartFile file, String courseId, CourseVideo courseVideo) {
        if (courseId == null || courseId.equals("")) {
            return new FdfsResult(CommonCode.INVALID_PARAM, null);
        }
        if (file.isEmpty()) {
            return new FdfsResult(CommonCode.FILE_IS_NULL, null);
        }
        String path = null;
        try {
            path = commonFileUtil.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);

        //获取原来文件名称
        String fileSuffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        if (fileSuffix.equals(".mp4")) {
            courseVideo.setVideoId(new UUIDUtil().generateUUID());
            courseVideo.setFormat(fileSuffix.replace(".", ""));
            courseVideo.setSize(file.getSize());
            courseVideo.setUrl(path);
            ResponseResult responseResult = courseClient.uploadVideo(courseVideo, courseId);
            if (responseResult.getCode() == 10000) {
                return new FdfsResult(CommonCode.SUCCESS, path);
            }
            return new FdfsResult(CommonCode.FAIL, null);
        } else if (fileSuffix.equals(".png") || fileSuffix.equals(".jpg") || fileSuffix.equals(".jpeg")) {
            CourseImage courseImage = new CourseImage();
            try {
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                if (bufferedImage == null) {
                    return new FdfsResult(CommonCode.FILE_IS_NULL, null);
                }
                int width = bufferedImage.getWidth();
                if (width == 263) {
                    courseImage.setImageId(new UUIDUtil().generateUUID());
                    courseImage.setSmallImage(path);
                    courseImage.setIsBanner(0);
                    ResponseResult responseResult = courseClient.uploadImage(courseImage, courseId);
                    if (responseResult.getCode() == 10000) {
                        return new FdfsResult(CommonCode.SUCCESS, path);
                    }
                }
                return new FdfsResult(CommonCode.DATABASE_ERROR, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new FdfsResult(CommonCode.FILE_FORMAT_ERROR, null);
    }

    @Override
    public FdfsResult uploadFileToFastDFS(MultipartFile file) {
        if (file.isEmpty()) {
            return new FdfsResult(CommonCode.FILE_IS_NULL, null);
        }
        String path = null;
        try {
            path = commonFileUtil.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        return new FdfsResult(CommonCode.SUCCESS, path);
    }

}
