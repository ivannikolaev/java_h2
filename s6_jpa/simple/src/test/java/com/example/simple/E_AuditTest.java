package com.example.simple;

import com.example.simple.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest(classes = SimpleApplication.class)
@Sql(value = "classpath:sql/init_client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class E_AuditTest {

    @Autowired
    private PostService service;

    @Test
    void test() throws InterruptedException {
        List<Future<Object>> futures = Executors.newFixedThreadPool(2).invokeAll(List.of(
                () -> service.updatePostNameWithTimeout(2L, "Title 1"),
                () -> service.updatePostNameWithoutTimeout(2L, "Title 2")
        ));

        Post postById = service.getPostById(2L);
        log.info("Result title: {}", postById.getTitle());

        // смотрим в логи и видим, что у нас lost update?

        // рассказываем про оптимистик блокировки и идем в сущности
        // Comment, Image, Post и класс SimpleApplication раскомментим аудит

        futures.forEach(x -> {
            try {
                log.info("Future result {}", x.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
