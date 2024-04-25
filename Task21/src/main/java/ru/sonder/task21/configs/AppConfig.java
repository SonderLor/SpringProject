package ru.sonder.task21.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.sonder.task21.repositories")
@EntityScan(basePackages = "ru.sonder.task21.models")
@ComponentScan(basePackages = "ru.sonder.task21")
public class AppConfig {}
