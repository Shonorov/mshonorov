package ru.job4j.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring framework web part configuration.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/statistic").setViewName("statistic");
        registry.addViewController("/redirect").setViewName("redirect");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/help").setViewName("help");

    }


}
