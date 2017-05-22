package net.sinyoo.center.web;

import net.sinyoo.center.web.filter.SessionFilter;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.MultipartConfigElement;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/6
 * Time: 下午2:02
 */
@SpringBootApplication(scanBasePackages="net.sinyoo.center.web",exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ImportResource(locations = {"dubbo-consumer.xml"})
public class WebApplication {

    private static Logger logger = Logger.getLogger(WebApplication.class);

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SessionFilter sessionFilter = new SessionFilter();
        registrationBean.setFilter(sessionFilter);
        return registrationBean;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize("4MB"); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("4MB");
        // Sets the directory location where files will be stored.
        // factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }

    /**
     * Main Start
     */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
//        AccessTokenCache.addCache("623751D996C14763B8D6C9158442C07B",1);
        System.out.println("============= SpringBoot web Start Success =============");
    }
}
