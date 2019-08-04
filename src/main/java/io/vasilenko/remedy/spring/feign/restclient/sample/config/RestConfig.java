package io.vasilenko.remedy.spring.feign.restclient.sample.config;

import feign.Feign;
import feign.gson.GsonDecoder;
import io.vasilenko.remedy.spring.feign.restclient.sample.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Value("${base_url}")
    private String baseUrl;

    @Bean
    public UserService userService() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(UserService.class, baseUrl + "/vasilenkosergey/fake-online-rest-server/users");
    }
}
