package net.sinyoo.center.core.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * Created by yunpengsha on 2017/3/20.
 */
public class UploadUtils {

    private static final OSSClientPool pool = OSSClientPool.getPool();

    public String uploadPicture(File file , String filename){

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //从链接池中取得一个对象
        OSSClient ossClient = pool.getOSSClient();
        PutObjectResult p = ossClient.putObject("sharing-test",filename, inputStream);
        // 设置URL过期时间设置为2000年
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 2000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl("sharing-test", filename, expiration);
        // ossclient对象返回链接池中
        pool.addOSSClient(ossClient);

        return url.toString();

//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        PutObjectResult p = ossClient.putObject("sharing-test",filename, inputStream);
//        // 设置URL过期时间设置为2000年
//        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 2000);
//        // 生成URL
//        URL url = ossClient.generatePresignedUrl("sharing-test", filename, expiration);
//
//        ossClient.shutdown();
//
//        return url.toString();
    }

    public String uploadPicture(ByteArrayInputStream inputStream , String filename){

        OSSClient ossClient = pool.getOSSClient();
        PutObjectResult p = ossClient.putObject("sharing-test",filename, inputStream);
        // 设置URL过期时间设置为2000年
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 2000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl("sharing-test", filename, expiration);

        pool.addOSSClient(ossClient);

        return url.toString();
    }

    //删除文件
    public void deletePictureByKey(String filename){
        OSSClient ossClient = pool.getOSSClient();
        ossClient.deleteObject("sharing-test",filename);
        pool.addOSSClient(ossClient);
    }

//    public static void main(String[] args){
//    //    new Upload().deletePictureByKey("nice.png");
//
//        String s = new Upload().uploadPicture(new File("C:/a.jpg"), "nice.png");
//        System.out.println(s);
//    }
}
