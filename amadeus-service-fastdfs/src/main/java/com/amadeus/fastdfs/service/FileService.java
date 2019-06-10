package com.amadeus.fastdfs.service;

import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.FdfsResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FdfsResult fileUpload(MultipartFile file, String courseId, CourseVideo courseVideo);

    FdfsResult uploadFileToFastDFS(MultipartFile file) throws IOException;

}
