package com.bibliotheque.microservicemyclient.configurations;

import com.bibliotheque.microservicemyclient.exeptions.CustumErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionConfig {

    @Bean
    public CustumErrorDecoder custumErrorDecoder(){
        return new CustumErrorDecoder();
    }
}
