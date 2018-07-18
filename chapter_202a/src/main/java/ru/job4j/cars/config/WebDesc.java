package ru.job4j.cars.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebDesc extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                SpringRootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                SpringWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "*.do"
        };
    }

    //TODO Add filter for UTF8 encoding.
}
