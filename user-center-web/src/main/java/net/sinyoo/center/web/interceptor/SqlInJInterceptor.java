package net.sinyoo.center.web.interceptor;

import net.sinyoo.center.core.util.SqlInj;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * 防止sql注入拦截器
 * 创建用户:     wangHui
 * 创建时间:     2017-03-14
 * Created by IntelliJ IDEA.
 */
public class SqlInJInterceptor implements HandlerInterceptor {


    // preHandle()方法在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO 获取参数列表  校验参数
        Map<String, String[]> parameterMap = request.getParameterMap();


        if (parameterMap.isEmpty()) {
            return true;
        }

        if (!parameterMap.isEmpty()){
            Set<Map.Entry<String, String[]>> parameterSet = parameterMap.entrySet();
            for (Map.Entry<String, String[]> paramString : parameterSet) {
                String[] parameterValues = paramString.getValue();
                if (parameterValues != null && parameterValues.length > 0) {
                    for (String parameterValue : parameterValues) {
                        if (!SqlInj.sql_inj(parameterValue)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // postHandle()方法在业务处理器处理请求之后被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    // afterCompletion()方法在DispatcherServlet完全处理完请求后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }



    /**
     * 校验参数
     * @param request
     * @return 不含有sql返回true
     */
    private boolean validateSql(HttpServletRequest request) {
        //获得所有请求参数名
        Enumeration params = request.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = request.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }

        return sqlValidate(sql);
    }

    /**
     * 校验参数是否含有sql语句
     * @param str
     * @return 不含有sql返回true
     */
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) <= 0) {
                return true;
            }
        }
        return false;
    }

}