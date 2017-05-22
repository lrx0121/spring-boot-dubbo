package net.sinyoo.center.web.filter;

import net.sinyoo.center.core.api.ApiResponse;
import net.sinyoo.center.core.base.ResponseErrorCode;
import net.sinyoo.center.core.util.StringUtils;
import net.sinyoo.center.web.utils.AccessTokenUtil;
import net.sinyoo.center.web.utils.PublicUrl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午1:30
 */
public class SessionFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        rep.setHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
        rep.setHeader("Access-Control-Allow-Origin", "*");
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT, DELETE");
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,accessToken");
        rep.setHeader("Access-Control-Allow-Credentials","true");
        if (Objects.equals(req.getMethod(), "OPTIONS")){
            return;
        }

        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        System.out.println(session.getId());
        if (!PublicUrl.isPublicUri(uri)) {
//            String accessToken = AccessTokenUtil.getAccessToken(req);
            if (StringUtils.isEmpty(AccessTokenUtil.getAccessToken(req))) {
                sessionOut(req, rep);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void sessionOut(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        //如果判断是 AJAX 请求,直接设置为session超时
        if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            printJson(req, rep);
        }else {
            printJson(req, rep);
        }
    }

    private void printJson(HttpServletRequest request,
                           HttpServletResponse response) {
        ApiResponse apiResponse = new ApiResponse();
        String resultJson = apiResponse.getErrorResponse(ResponseErrorCode.LOGIN, ResponseErrorCode.LOGIN_MESSAGE, ResponseErrorCode.LOGIN, ResponseErrorCode.LOGIN_MESSAGE);
        OutputStream writer = null;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            writer = response.getOutputStream();
            writer.write(resultJson.getBytes("UTF-8"));
//			System.out.println(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            String simplename = ex.getClass().getSimpleName();
            if (!"ClientAbortException".equals(simplename)) {
                ex.printStackTrace();
            }
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
