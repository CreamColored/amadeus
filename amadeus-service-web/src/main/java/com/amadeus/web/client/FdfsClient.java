package com.amadeus.web.client;

import com.amadeus.framework.model.response.FdfsResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.config.FeignSupportConfiguration;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "AMADEUS-SERVICE-FASTDFS", configuration = FeignSupportConfiguration.class)
@RequestMapping("/api/fdfs")
public interface FdfsClient {
    //上传图片到fdfs
    @PostMapping(value = "/uploadFileToFastDFS", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FdfsResult uploadFileToFastDFS(@RequestPart MultipartFile file);

    //上传视频到fdfs
    @PostMapping("/fileUpload")
    ResponseResult fileUpload(@RequestParam MultipartFile file);


}
