package com.example.MSA_test.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//@Configuration
//public class StaticResourceConfig {
//    @Bean
//    public RouterFunction<ServerResponse> staticResourceRouter() {
//        return RouterFunctions.resources("/**", new ClassPathResource("static/"));
//    }
//}