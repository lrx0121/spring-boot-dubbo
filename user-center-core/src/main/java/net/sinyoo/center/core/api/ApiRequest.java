package net.sinyoo.center.core.api;

import net.sinyoo.center.core.base.BaseRequest;
import net.sinyoo.center.core.exception.ApiException;

/**
 * api 请求规范类
 * 创建用户:     wangHui
 * 创建时间:     2017-03-14
 * Created by IntelliJ IDEA.
 */
public class ApiRequest extends BaseRequest {
    @Override
    public boolean checkUpdateParams() throws ApiException {
        return ApiUtils.checkUpdateParams(this);
    }

    @Override
    public void checkParams() throws ApiException {
        ApiUtils.checkParams(this);
    }
}
