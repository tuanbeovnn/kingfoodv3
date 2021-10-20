package com.kingfood.backend.securityconfig.oath2.config;

import com.kingfood.backend.constants.AppConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
@Order(2)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer){
        resourceServerSecurityConfigurer.resourceId(AppConstant.ResourceServer.RESOURCE_ID).stateless(false);
    }

    /*public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .anonymous().disable()
                .formLogin()
                .defaultSuccessUrl("/swagger-ui.html")
                .failureUrl("/login?error=true")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }*/
}
