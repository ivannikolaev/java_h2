package com.example.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Slf4j
@SpringBootTest(classes = SimpleApplication.class)
@Sql(value = "classpath:sql/init_client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class B_NPlusOneTest {

    @Autowired
    private PostService postService;

    @Test
    void test() {
        List<PostCommentDto> images = postService.getPostByCommentText("cool");

        log.info("Retrieved images: {}", images);

        // посмотрим в логи на запросы, их там МНОГО!

        // исправим ситуацию через EntityGraph и используем закоменченный код
        // List<PostCommentDto> imagesAfterChanges = postService.getPostByCommentTextWithEntityGraph("cool");
        // log.info("Retrieved images: {}", imagesAfterChanges);

        // смотрим на логи и радуемся
    }
}
