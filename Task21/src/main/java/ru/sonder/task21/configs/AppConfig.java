package ru.sonder.task21.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableJpaRepositories(basePackages = "ru.sonder.task21.repositories")
@EntityScan(basePackages = "ru.sonder.task21.models")
@ComponentScan(basePackages = "ru.sonder.task21")
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfig {}
