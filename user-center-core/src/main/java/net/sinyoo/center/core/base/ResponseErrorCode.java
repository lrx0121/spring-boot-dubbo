package net.sinyoo.center.core.base;

/**
 * 返回给客户端的subErrorCode
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午1:23
 */
public class ResponseErrorCode {

    /**
     * 未知错误
     */
    public static final Integer UNKNOWN_ERROR = 1000;
    public static final String UNKNOWN_ERROR_MESSAGE = "未知错误";

    /**
     * 需要登录
     */
    public static final Integer LOGIN = 1001;
    public static final String LOGIN_MESSAGE = "登录token已过期,请重新登录";

    /**
     * 参数缺失
     */
//    public static final String PARAM_LOST = "1002";
//    public static final String PARAM_LOST_MESSAGE = "请求参数缺失";

    /**
     * 参数错误
     */
    public static final Integer PARAM_ERROR = 1003;
    public static final String PARAM_ERROR_MESSAGE = "请求参数错误";

    /**
     * 服务层抛出异常
     */
    public static final Integer SERVICE_ERROR = 1004;
    public static final String SERVICE_ERROR_MESSAGE = "服务层校验出错";


    /**
     * 检查信息有问题,返回的错误
     */
    public static final Integer CHECK_ERROR = 1005;
    public static final String CHECK_ERROR_MESSAGE = "校验请求不合法";


}
