package com.kingfood.backend.securityconfig.oath2.config;

import com.kingfood.backend.securityconfig.interceptor.GatewayInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final String ROOT = "/var/www/live/";

    @Autowired
    private GatewayInterceptor gatewayInterceptor;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/image/**")
                .addResourceLocations("file:" + ROOT + "/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }

    /**
     * final InterceptorRegistry registry
     *
     * @param
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(gatewayInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
                        , "/webjars/**", "/csrf", "/", "/error");
    }

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(InternalResourceView.class);
        return viewResolver;
    }

}
