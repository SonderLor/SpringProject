package ru.sonder.task15.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.sonder.task15.repositories")
@EntityScan(basePackages = "ru.sonder.task15.models")
@ComponentScan(basePackages = "ru.sonder.task15")
public class AppConfig {}
