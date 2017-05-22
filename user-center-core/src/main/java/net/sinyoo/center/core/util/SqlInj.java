package net.sinyoo.center.core.util;

/**
 * 校验字段参数是否为sql
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/2/21
 * Time: 上午10:27
 */
public class SqlInj {

    /**
     * 校验参数中是否含有注入的sql
     * @param str
     * @return
     */
    public static boolean sql_inj(String str) {
        String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|--|+|,";
        //这里的东西还可以自己添加
        if (str== null){
            return true;
        }
        str = str.toLowerCase();

        String[] inj_stra = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {

            if (str.indexOf(inj_stra[i]) >= 0) {
                return false;
            }
        }
        return true;
    }
}
