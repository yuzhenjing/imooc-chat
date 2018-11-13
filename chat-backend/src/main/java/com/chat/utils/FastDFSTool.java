package com.chat.utils;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.FileInfo;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.UUID;

public class FastDFSTool {

    /**
     * 配置文件
     */
    public static String conf_filename = "E:\\yongda\\imooc-chat\\chat-backend\\src\\main\\resources\\fdfs-client.conf";

    /**
     * 本地文件
     */
    public String local_filename = "E:/test.txt";

    /**
     * 上传文件到FastDFS
     *
     * @param bs
     * @param filename
     * @return
     */
    public static String uploadFile(byte[] bs, String filename) {
        // 获得classpath下文件的绝对路径
        String upload_file1 = null;
        try {
            ClientGlobal.init(conf_filename);

            // 创建跟踪客户端
            TrackerClient trackerClient = new TrackerClient();
            // 通过跟踪客户端取得连接获得跟踪服务器端
            TrackerServer connection = trackerClient.getConnection();
            //存储客户端
            StorageClient1 storageClient = new StorageClient1(connection, null);
            // 获得文件名的扩展名
            final String suffix = filename.substring(filename.indexOf("."));
            upload_file1 = storageClient.upload_file1(bs, suffix, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upload_file1;
    }


    public static void main(String[] args) throws IOException {

        InputStream in = new FileInputStream("d:/timg.jpg");
        byte[] b = new byte[in.available()];
        in.read(b);
        System.out.println( uploadFile(b,"timg.jpg"));


    }
    public void downloadFile() {
        try {
            //初始化客户端(通过文件系统下的配置文件)
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] b = storageClient.download_file("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
            System.out.println(b);
            IOUtils.write(b, new FileOutputStream("D:/" + UUID.randomUUID().toString() + ".conf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public FileInfo getFileInfo() {
        FileInfo fi = null;
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            storageClient.get_file_info("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fi;
    }


    public NameValuePair[] getFileMate() {
        NameValuePair[] nvps = null;
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            nvps = storageClient.get_metadata("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
            for (NameValuePair nvp : nvps) {
                System.out.println(nvp.getName() + ":" + nvp.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nvps;
    }


    public int DeleteFile() {
        Integer i = null;
        try {
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            //删除成功返回0
            i = storageClient.delete_file("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
            System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
