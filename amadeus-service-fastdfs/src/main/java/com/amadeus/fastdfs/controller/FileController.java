package com.amadeus.fastdfs.controller;

import com.amadeus.fastdfs.service.FileService;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.FdfsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/fdfs")
@Api(value = "FastDFS文件管理", tags = "文件上传与下载")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload/{courseId}")
    @ApiOperation(value = "Multipart文件上传", notes = "参数位MultipartFile")
    public FdfsResult fileUpload(@RequestParam MultipartFile file, @PathVariable("courseId") String courseId, @ModelAttribute("courseVideo") CourseVideo courseVideo) {
        return fileService.fileUpload(file, courseId, courseVideo);
    }

    @PostMapping("/uploadFileToFastDFS")
    @ApiOperation(value = "使用fastdfs进行文件上传", notes = "参数位MultipartFile,返回值为ResponseResult")
    public FdfsResult uploadFileToFastDFS(@RequestParam MultipartFile file) throws IOException {
        return fileService.uploadFileToFastDFS(file);
    }

}
