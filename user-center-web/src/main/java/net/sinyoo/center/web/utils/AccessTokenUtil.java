package net.sinyoo.center.web.utils;

import net.sinyoo.center.core.util.StringUtils;
import net.sinyoo.center.web.cache.AccessTokenCache;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 访问token工具
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/23
 * Time: 下午2:02
 */
public class AccessTokenUtil {


    /**
     * 获取请求head  或者cookie中的accessToken
     * @param request
     * @return
     */
    public static String getAccessToken(HttpServletRequest request){
        String name = "accesstoken";
        Map<String,String> headers = getHeadersInfo(request);
        String accessToken = headers.get(name);
        if (StringUtils.isEmpty(accessToken)){
            //获取cookie中的token
            Cookie cookie = CookieUtil.getCookieByName(request, name);
            if (cookie == null){
                return null;
            }
            accessToken = cookie.getValue();
            if (!AccessTokenCache.hasAccessToken(accessToken)){
                return null;
            }
        }else {
            if (!AccessTokenCache.hasAccessToken(accessToken)){
                return null;
            }
        }
        return accessToken;
    }


    private static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 在缓存中存储用户的id   在cookie中设置用户的accessToken
     * @param response
     * @param accessToken
     * @param userId
     */
    public static void setAccessToken(HttpServletResponse response,String accessToken,Integer userId){
        String name = "accessToken";
        AccessTokenCache.addCache(accessToken,userId);
        CookieUtil.addCookie(response,name,accessToken,2 * 60 * 60);
    }

//    public static String getPictureToken(HttpServletRequest request){
//        String name = "pictureToken";
//        String pictureToken = request.getHeader(name);
//        if (StringUtils.isEmpty(pictureToken)){
//            //获取cookie中的token
//            Cookie cookie = CookieUtil.getCookieByName(request, name);
//            if (cookie == null){
//                return null;
//            }
//            return cookie.getValue();
//        }
//        return pictureToken;
//    }

//    public static void setPictureToken(HttpServletResponse response,String accessToken,String code){
//        String name = "pictureToken";
//        PictureTokenCache.addCache(accessToken,code);
//    }

}
