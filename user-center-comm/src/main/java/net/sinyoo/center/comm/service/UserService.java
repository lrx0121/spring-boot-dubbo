package net.sinyoo.center.comm.service;

import net.sinyoo.center.comm.model.User;
import net.sinyoo.center.core.exception.ServiceException;

/**
 * 用户服务
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/4/11
 * Time: 下午3:33
 */
public interface UserService {


    /**
     * 用户登录接口
     * @param userName
     * @param password
     * @return
     * @throws ServiceException
     */
    User login(String userName, String password) throws ServiceException;

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     * @throws ServiceException
     */
    User findByName(String userName) throws ServiceException;
}
