package net.sinyoo.center.web.config;

import net.sinyoo.center.web.interceptor.SqlInJInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * 拦截器配置
 * 跨域访问配置
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午1:03
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new SqlInJInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    /**
     * 允许跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, accessToken")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
