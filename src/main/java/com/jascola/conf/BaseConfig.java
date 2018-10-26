package com.jascola.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(value = {"com.jascola.job"})
@EnableScheduling
public class BaseConfig {

}
