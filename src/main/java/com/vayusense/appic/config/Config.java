package com.vayusense.appic.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;*/

@Configuration
//@Profile("actuator-endpoints")
public class Config {//extends WebSecurityConfigurerAdapter {

        @Bean
        public HttpTraceRepository httpTraceRepository() {
            return new InMemoryHttpTraceRepository();
        }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/actuator/**");
    }*/


}




