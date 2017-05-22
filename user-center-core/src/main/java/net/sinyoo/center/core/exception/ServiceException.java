package net.sinyoo.center.core.exception;


import net.sinyoo.center.core.util.StringUtils;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 5876514054317905482L;


    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer errorCode, String message) {
        super(errorCode + ":" + message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }


    public static Integer getErrorCode(String message) {
        if (StringUtils.isEmpty(message)) {
            return 500;
        }
        if (!message.contains(":")) {
            return 500;
        }
        return Integer.parseInt(message.split(":")[0]) - 100;
    }

    public static String getErrorMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return "操作失败";
        }
        if (!message.contains(":")) {
            return message;
        }
        String[] datas = message.split(":");
        if (datas.length == 2) {
            return datas[1];
        }
        return "";
    }
}
