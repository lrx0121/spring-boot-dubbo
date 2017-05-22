package net.sinyoo.center.web.request;

import net.sinyoo.center.core.annotation.ApiField;
import net.sinyoo.center.core.annotation.NotEmpty;
import net.sinyoo.center.core.api.ApiRequest;

/**
 * 用户登录请求
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/4/11
 * Time: 下午4:01
 */
public class UserLoginRequest extends ApiRequest {

    @ApiField
    @NotEmpty(message = "请输入用户名")
    private String userName;

    @ApiField
    @NotEmpty(message = "请输入密码")
    private String password;

    public UserLoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
