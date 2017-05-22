package net.sinyoo.center.core.util;

import com.aliyun.oss.OSSClient;

import java.util.LinkedList;

/**
 * 获取阿里云oss链接的链接池
 * Created by yunpengsha on 2017/3/27.
 */
public class OSSClientPool {
    // 自定义链接数 10
    private  final int size = 10;

    private  final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com/";

    private  final String accessKeyId = "qelkKL9GgZaH0Ea9";

    private  final String accessKeySecret = "f3lkRgQJZAP24xPoGdbWFIaLoBDdTx";

    private  final LinkedList<OSSClient> list = new LinkedList<OSSClient>();

    private static OSSClientPool ossClientPool;

    private OSSClientPool(){
        initPool();
    }

    //获取链接
    public  OSSClient getOSSClient(){

        synchronized("getOSSClient"){
            OSSClient ossClient = null;
            while( true ){
                if( (ossClient = list.pollFirst()) != null ){
                    return ossClient;
                }
            }
        }
    }


    public synchronized void addOSSClient(OSSClient ossClient){
        list.offerLast(ossClient);
    }

    public static OSSClientPool getPool(){
        if( ossClientPool == null ){
            synchronized ( OSSClientPool.class ){
                if( ossClientPool == null){
                    ossClientPool = new OSSClientPool();
                }
            }
        }
        return ossClientPool;
    }

    private void initPool() {
        for(int i = 0;i < size ; i++){
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            list.add(ossClient);
        }
    }

//    public static void main(String[] args){
//        OSSClientPool pool = OSSClientPool.getPool();
//        OSSClient ossClient ;
//        for(int i=0 ; i < 10;i++){
//            ossClient=pool.getOSSClient();
//        }
//    }
}
