package net.sinyoo.center.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@MapperScan("net.sinyoo.center.service")
@SpringBootApplication
@ImportResource(locations = {"dubbo-provider.xml"})
public class ServiceApplication {

    private static volatile boolean running = true;

    private static Logger logger = Logger.getLogger(ServiceApplication.class);

    //DataSource配置  
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new com.alibaba.druid.pool.DruidDataSource();
    }

    //提供SqlSession
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis-mapping/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * Main 方法启动项
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
        System.out.println("============= APP Start ON SpringBoot Success =============");
        synchronized (ServiceApplication.class) {
            while (running) {
                try {
                    ServiceApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

}  