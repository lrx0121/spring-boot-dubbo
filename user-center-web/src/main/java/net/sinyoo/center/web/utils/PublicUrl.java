package net.sinyoo.center.web.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 添加publicUrl   访问他不需要登录  不需要token
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午1:39
 */
public class PublicUrl {

    /**
     * 公共的uri不需要权限验证的
     */
    public static final Set<String> publicUris = new HashSet<String>();

    static {
        publicUris.add("/user/login");// 用户登录
        publicUris.add("/userRegister/getSmsCode/");//发送短信验证码
        publicUris.add("/userRegister/addBaseInfo");//用户注册
        publicUris.add("/userRegister/resetPassword");//忘记密码，重置


        publicUris.add("/homeLink");// 首页数据
        publicUris.add("/user/homeLinkDoctorInfo");//首页 医生信息
        publicUris.add("/subjectList/homeLinkList");//首页  科研列表
        publicUris.add("/subject/homeSubjectDetail");//首页   科研详情信息
        publicUris.add("/subjectPartIn/homeAgreeList");//首页  科研参与人列表



        publicUris.add("/imageVerification/getPicture");// 获得图片验证码
        publicUris.add("/imageVerification/validatePictureCode");//验证图片验证码
        publicUris.add("/imageVerification/getPictureAgain/");//获得图片验证码
    }

    /**
     * 判断是否可以直接访问的uri地址<br>
     * 将来应该使用cdn的方式，不能每次请求都进入java，这样效率太低
     *
     * @param uri
     * @return
     */
    public static synchronized boolean isPublicUri(String uri) {
        if (uri.equals("/"))
            return true;
        for (String _uri : publicUris) {
            if (uri.startsWith(_uri)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(isPublicUri("/userRegister/getSmsCode/18888888888"));
    }
}

