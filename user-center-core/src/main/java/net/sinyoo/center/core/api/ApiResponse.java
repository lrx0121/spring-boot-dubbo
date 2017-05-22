package net.sinyoo.center.core.api;

import net.sinyoo.center.core.base.ResponseErrorCode;
import net.sinyoo.center.core.exception.ApiException;
import net.sinyoo.center.core.base.BaseResponse;
import net.sinyoo.center.core.exception.ServiceException;
import net.sinyoo.center.core.util.GsonUtil;

/**
 * api返回规范类
 * 创建用户:     wangHui
 * 创建时间:     2017-03-14
 * Created by IntelliJ IDEA.
 */
public class ApiResponse extends BaseResponse {

    private Integer status = 0;

    private Integer errorCode;

    private String errorMessage;

    private Integer subCode;

    private String subMessage;

    public Integer getStatus() {
        return status;
    }

    private void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    private void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCode(Integer subCode) {
        this.subCode = subCode;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    @Override
    public String getUnKnowErrorResponse(Integer subCode, String subMessage) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.UNKNOWN_ERROR;
        this.errorMessage = ResponseErrorCode.UNKNOWN_ERROR_MESSAGE;
        this.subCode = subCode;
        this.subMessage = subMessage;
        return convertJson();
    }

    @Override
    public String getErrorResponse(Integer errorCode, String errorMessage, Integer subCode, String subMessage) {
        this.status = 1;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.subCode = subCode;
        this.subMessage = subMessage;
        return convertJson();
    }


    @Override
    public String getErrorResponse(ServiceException e) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.SERVICE_ERROR;
        this.errorMessage = ResponseErrorCode.SERVICE_ERROR_MESSAGE;
        this.subCode = ApiException.getErrorCode(e.getMessage());
        this.subMessage = ApiException.getErrorMessage(e.getMessage());
        return convertJson();
    }

    @Override
    public String getErrorResponse(ApiException e) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.PARAM_ERROR;
        this.errorMessage = ResponseErrorCode.PARAM_ERROR_MESSAGE;
        this.subCode = ApiException.getErrorCode(e.getMessage());
        this.subMessage = ApiException.getErrorMessage(e.getMessage());
        return convertJson();
    }

    @Override
    public String getSuccessResponse() {
        return convertJson();
    }

    private String convertJson() {
        return GsonUtil.getGsonInstance().toJson(this);
    }
}
