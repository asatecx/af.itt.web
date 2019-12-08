package af;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

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
}
