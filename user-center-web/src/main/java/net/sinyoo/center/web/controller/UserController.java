package net.sinyoo.center.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import net.sinyoo.center.comm.model.User;
import net.sinyoo.center.comm.service.UserService;
import net.sinyoo.center.core.exception.ApiException;
import net.sinyoo.center.core.exception.ServiceException;
import net.sinyoo.center.web.request.UserLoginRequest;
import net.sinyoo.center.web.response.UserLoginResponse;
import net.sinyoo.center.web.to.UserTo;
import net.sinyoo.center.web.utils.AccessTokenUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/4/11
 * Time: 下午4:06
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Reference
    private UserService userService;

    /**
     * <p>用户登录接口</p>
     * <p>request url   http://localhost:30101/user/login </p>
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param request             <p>{"username":"13585964979","password":"123456"}</p>
     * @param response            <p>error:    {"status":1,"errorCode":1003,"errorMessage":"请求参数错误","subCode":211,"subMessage":"请输入用户名"}</p>
     *                            <p>success:  {"accessToken":"E34892934E5C480084D1096C3587A0A2","data":{"userId":1,"phone":"13585964979","password":"9CDAA58884E1697F5BE2D532F328C841","userName":"ssss","gender":0,"userType":"DOCTOR","userStatus":0},"status":0}</p>
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody UserLoginRequest request, UserLoginResponse response) {

        //校验参数完整性
        try {
            request.checkParams();
        } catch (ApiException e) {
            return response.getErrorResponse(e);
        }
        //请求服务,设置返回的数据
        try {
            User user = userService.login(request.getUserName(), request.getPassword());
            UserTo userTo = new UserTo();
            BeanUtils.copyProperties(user, userTo);
            response.setUserTo(userTo);
        } catch (ServiceException e) {
            return response.getErrorResponse(e);
        }

        //设置访问token  缓存用户主键
        String accessToken = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        response.setAccessToken(accessToken);
        AccessTokenUtil.setAccessToken(httpServletResponse, accessToken, response.getUserTo().getUserId());

        //返回成功数据
        return response.getSuccessResponse();
    }
}
