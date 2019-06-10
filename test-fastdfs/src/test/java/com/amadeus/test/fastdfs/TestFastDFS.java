package com.amadeus.test.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFastDFS {
    //上传文件
    @Test
    public void testUploadFile() {
        try {
            //加载fastdfs-client.properties
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storage
            StorageServer storeServer = trackerClient.getStoreStorage(trackerServer);
            //创建storageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storeServer);
            //向storage服务器上传文件
            //本地文件的路径
            String filePath = "C:\\Users\\LaYohdaStasella\\Desktop\\test.txt";
            //上传成功后拿到文件Id
            String fileId = storageClient1.upload_file1(filePath, "txt", null);
            System.out.println(fileId);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    //下载文件
    @Test
    public void testDownloadFile() {
        //加载fastdfs-client.properties
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storage
            StorageServer storeServer = trackerClient.getStoreStorage(trackerServer);
            //创建storageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storeServer);
            //下载文件
            //文件id
            String fileId = "group1/M00/00/00/wKgrulykFS2AFXsqAAAAE0N9ksE975.txt";
            byte[] bytes = storageClient1.download_file1(fileId);
            //使用输出流保存文件
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\LaYohdaStasella\\Desktop\\test.txt"));
            fileOutputStream.write(bytes);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

    }
}
