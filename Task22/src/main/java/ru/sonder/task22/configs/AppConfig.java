package ru.sonder.task22.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaRepositories(basePackages = "ru.sonder.task22.repositories")
@EntityScan(basePackages = "ru.sonder.task22.models")
@ComponentScan(basePackages = "ru.sonder.task22")
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class AppConfig {}
