package com.jack.winter;

import com.jack.winter.config.ServiceProperties;
import com.jack.winter.config.datasource.JackConfig;
import com.jack.winter.config.datasource.TomConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan(basePackages = {"com.jack.winter.config","com.jack.winter.rest"})
@EnableAutoConfiguration
@SpringBootConfiguration
//@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(value = {ServiceProperties.class, JackConfig.class, TomConfig.class})
public class WinterApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(WinterApplication.class);

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(WinterApplication.class);
        final Environment env = app.run(args).getEnvironment();
        LOGGER.info("Application start profile is {}", env.getProperty("spring.profiles.active"));
    }

}

