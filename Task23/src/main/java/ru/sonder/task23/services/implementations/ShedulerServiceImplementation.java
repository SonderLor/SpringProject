package ru.sonder.task23.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sonder.task22.services.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@Service
@ManagedResource
@RequiredArgsConstructor
public class ShedulerServiceImplementation implements ShedulerService {
    private final UserService userService;
    private final PostService postService;
    private final ObjectMapper mapper;

    @Value("${application.persistent-dir}")
    private String persistentDir;

    @SneakyThrows
    @ManagedOperation
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    @Override
    public void persist() {
        File dir = new File(persistentDir);
        if (dir.exists()) {
            FileUtils.deleteDirectory(dir);
        }
        dir.mkdirs();
        new File(dir, "users").mkdir();
        new File(dir, "posts").mkdir();
        saveAllEntities(dir);
    }

    private void saveAllEntities(File dir) {
        userService.getAllUsers().forEach(
                user -> {
                    try {
                        Files.write(dir.toPath().resolve("users").resolve(user.getId() + ".json"),
                                mapper.writeValueAsBytes(user)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        postService.getAllPosts().forEach(
                post -> {
                    try {
                        Files.write(dir.toPath().resolve("posts").resolve(post.getId() + ".json"),
                                mapper.writeValueAsBytes(post)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
