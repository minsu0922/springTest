package com.example.springexam;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.net.PortUnreachableException;

@Configuration
public class webConfig {

    //패턴5번 validation 방법을 사용할때 사용되는
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();

        bean.setBasename("classpath:messages");
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }


}
