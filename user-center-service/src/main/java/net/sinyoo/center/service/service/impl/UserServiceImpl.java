package net.sinyoo.center.service.service.impl;

import net.sinyoo.center.comm.model.User;
import net.sinyoo.center.comm.service.UserService;
import net.sinyoo.center.core.exception.ServiceException;
import net.sinyoo.center.core.util.MD5Util;
import net.sinyoo.center.service.domain.UserDo;
import net.sinyoo.center.service.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/4/11
 * Time: 下午3:34
 */
@Component
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) throws ServiceException {
        UserDo userDo = userMapper.selectByPhone(userName);
        if (userDo == null) {
            throw new ServiceException(601, "用户不存在");
        }
        if (!MD5Util.MD5(password).equals(userDo.getPassword())) {
            throw new ServiceException(602, "帐号密码错误");
        }
        User user = new User();
        BeanUtils.copyProperties(userDo, user);
        return user;
    }

    @Override
    public User findByName(String userName) throws ServiceException {
        return null;
    }
}
