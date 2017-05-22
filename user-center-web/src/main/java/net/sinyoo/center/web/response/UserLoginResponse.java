package net.sinyoo.center.web.response;

import com.google.gson.annotations.SerializedName;
import net.sinyoo.center.core.api.ApiResponse;
import net.sinyoo.center.web.to.UserTo;

/**
 * 用户登录返回
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/4/11
 * Time: 下午4:03
 */
public class UserLoginResponse extends ApiResponse {


    private String accessToken;

    @SerializedName("data")
    private UserTo userTo;

    public UserLoginResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }
}
