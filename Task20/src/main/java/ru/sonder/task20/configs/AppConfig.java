package ru.sonder.task20.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.sonder.task20.repositories")
@EntityScan(basePackages = "ru.sonder.task20.models")
@ComponentScan(basePackages = "ru.sonder.task20")
public class AppConfig {}
