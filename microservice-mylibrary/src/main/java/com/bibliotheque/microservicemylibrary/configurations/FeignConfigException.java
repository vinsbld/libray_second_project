package com.bibliotheque.microservicemylibrary.configurations;

import com.bibliotheque.microservicemylibrary.exeptions.CustumErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfigException {
    @Bean
    public CustumErrorDecoder custumErrorDecoder(){
        return new CustumErrorDecoder();
    }
}
