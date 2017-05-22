package net.sinyoo.center.core.util;

import java.security.MessageDigest;

/**
 * Created by yunpengsha on 2017/3/21.
 */
public class MD5Util {

    private static final String SPOILER = "xinyoo";

//    public static void main(String[] args) {
//        System.out.println(MD5("123456"));
//    }

    public final static String MD5(String pwd) {
        pwd = pwd + SPOILER;
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = pwd.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args){
//        System.out.println(MD5Util.MD5("c123456"));
//    }

}
