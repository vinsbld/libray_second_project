package com.bibliotheque.microservicemylibrary.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("spring.mail")
public class EmailConfig {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private boolean debug;
}
