package com.bibliotheque.microservicemylibrary.batch;

import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.outils.EmailType;
import com.bibliotheque.microservicemylibrary.proxies.IMicroserviceMyUsersProxy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private IReservationDao iReservationDao;

    @Autowired
    private IEmailDao iEmailDao;

    @Autowired
    private IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;

    @Autowired
    private EmailType emailType;


    @Autowired
    private JavaMailSenderImpl sender;

    @Bean
    public Step stepOne() {
        return steps.get("stepOne")
                .tasklet(new MyTaskOne(iReservationDao, iEmailDao, iMicroserviceMyUsersProxy, emailType, sender) )
                .build();
    }

    @Bean
    public Job demoJob(){
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .build();
    }


}
