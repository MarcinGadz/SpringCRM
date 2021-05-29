package com.marcingadz.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan("com.marcingadz")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {
    private Environment env;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public DataSource dsource() {
        ComboPooledDataSource s = new ComboPooledDataSource();
        try {
            s.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
        s.setJdbcUrl(env.getProperty("jdbc.url"));
        s.setUser(env.getProperty("jdbc.user"));
        s.setPassword(env.getProperty("jdbc.password"));
        s.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        s.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        s.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        s.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
        return s;
    }

    private int getIntProperty(String name) {
        String prop = env.getProperty(name);
        if (prop == null) {
            return 0;
        }
        return Integer.parseInt(prop);
    }

    private Properties getHibernateProperties() {
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        p.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return p;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dsource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory session) {
        HibernateTransactionManager man = new HibernateTransactionManager();
        man.setSessionFactory(session);
        return man;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public Environment getEnv() {
        return env;
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}
