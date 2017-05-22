package net.sinyoo.center.web.cache;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/21
 * Time: 下午4:34
 */
public class AccessTokenCache {
    /**
     * 访问token缓存
     */
    private static Map<String, Long> accessTokenCache = new HashMap<>();


    /**
     *  登录用户编号缓存
     */
    private static Map<String, Integer> userIdCache = new HashMap<>();


    /**
     * 添加缓存
     * @param accessToken
     * @param userId
     */
    public static void addCache(String accessToken,Integer userId) {
        accessTokenCache.put(accessToken, Calendar.getInstance().getTimeInMillis() + 2 * 60 * 60 * 1000);
        userIdCache.put(accessToken,userId);
    }

    /**
     * 获取登录用户编号
     * @param accessToken
     * @return
     */
    public static Integer getUserId(String accessToken){
        return userIdCache.get(accessToken);
    }

    /**
     * 获取是否有缓存信息
     *
     * @param accessToken
     * @return
     */
    public static boolean hasAccessToken(String accessToken) {
        removeToken();
        if (accessTokenCache.get(accessToken) == null) {
            return false;
        } else {
            long time = accessTokenCache.get(accessToken);
            if (time <= System.currentTimeMillis()) {
                accessTokenCache.remove(accessToken);
                userIdCache.remove(accessToken);
                return false;
            } else {
                return true;
            }
        }
    }


    private synchronized static void removeToken() {
        List<String> needRemove = new ArrayList<>();
        Set<String> signSet = accessTokenCache.keySet();
        for (String mSign : signSet) {
            Long time = accessTokenCache.get(mSign);
            if (Calendar.getInstance().getTimeInMillis() > time) {
                needRemove.add(mSign);
            }
        }
        if (needRemove.size() > 0) {
            for (String signStr : needRemove) {
                accessTokenCache.remove(signStr);
                userIdCache.remove(signStr);
            }
        }
    }

}
