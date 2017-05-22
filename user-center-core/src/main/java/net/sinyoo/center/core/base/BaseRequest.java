package net.sinyoo.center.core.base;

import net.sinyoo.center.core.exception.ApiException;

import java.io.Serializable;

/**
 * Created by yunpengsha on 2017/3/14.
 */
public abstract class BaseRequest implements Serializable {

    /**
     * 校验更新参数  需要参数更新的字段请用ApiFiled注解
     * @throws ApiException
     */
    public abstract boolean checkUpdateParams() throws ApiException;
    /**
     * 校验参数
     * @throws ApiException
     */
    public abstract void checkParams() throws ApiException;

}
