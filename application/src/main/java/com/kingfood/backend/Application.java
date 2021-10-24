package com.kingfood.backend;




import com.kingfood.backend.securityconfig.interceptor.GatewayInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;


@SpringBootApplication()
@EnableJpaRepositories(basePackages = {"com.kingfood.backend.dbprovider"})
@EntityScan("com.kingfood.backend.domains")
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Application Started");
    }
    @Autowired
    private GatewayInterceptor gatewayInterceptor;

    @PostConstruct
    public void initDataGateWay() {
        gatewayInterceptor.initData();
    }
}