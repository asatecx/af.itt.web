package af;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@EnableAsync
@SpringBootApplication
@ComponentScan("af")
@MapperScan(basePackages ={"af.itt.sc.mapper"})
public class ApplicationStarter  {

    private static Log logger = LogFactory.getLog(ApplicationStarter.class);

    public static void main(String[] args) {
    	SpringApplication.run(ApplicationStarter.class, args);
    }


    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("AppContext initialized");
            }
            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                logger.info("AppContext destroyed");
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(false);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(300);
        filter.setBeforeMessagePrefix("Before process [");
        filter.setAfterMessagePrefix(" After  process [");
        return filter;
    }

    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> dawsonApiFilter() {
        FilterRegistrationBean<CommonsRequestLoggingFilter> registration = new FilterRegistrationBean<>();
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setBeforeMessagePrefix("Before request [");
        filter.setAfterMessagePrefix(" After  request [");
        registration.setFilter(filter);
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //slr.setDefaultLocale(Locale.JAPAN);
        return slr;
    }

//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("af/messages/message", "xxx/messages/message", "stella/messages/message");
//        messageSource.setCacheMillis(600);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        MessageUtil.setMessageSource(messageSource);
//        return messageSource;
//    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializerByType(Timestamp.class, new TimestampSerializer().withFormat(false, null));
//        builder.deserializerByType(Timestamp.class, new TimestampDeserializer());
//        builder.deserializerByType(java.util.Date.class, new DateDeserializer());
//        builder.featuresToEnable(MapperFeature.USE_STD_BEAN_NAMING);
//        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        builder.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        return builder;
//    }
}
