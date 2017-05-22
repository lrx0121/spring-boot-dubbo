package net.sinyoo.center.core.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/21
 * Time: 下午3:12
 */
public class ListToString {

    /**
     * list  转string字符串
     *
     * @param list
     * @return
     */
    public static String list2String(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list == null || list.size() <= 0) {
            return "";
        }
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append(list.get(i));
            if (i < list.size() - 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }
}
