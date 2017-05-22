package net.sinyoo.center.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午12:27
 */
public class GsonUtil {


    /**
     * 获取gson实例
     * @return
     */
    public static Gson getGsonInstance(){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
