package com.example.simple;

import com.example.simple.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@SpringBootTest(classes = SimpleApplication.class)
@Sql(value = "classpath:sql/init_client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class A_LazyInitializationExceptionTest {

    @Autowired
    private PostService postService;

    @Test
    // что будет, если мы стандартным методом вытянем Post и связанные сущности?
    void test() {
        long postId = 1L;
        Post post = postService.getPostById(postId);

        post.getImages().forEach(image -> log.info("Image id {}, url {}", image.getId(), image.getUrl()));
        // смотрим в логи видим ошибки, после попробуем раскомментить следующий код и выполнить

        // List<Image> images = post.getImages();
        //
        // log.info("Images: {}", images);

        // после чиним, реализуя метод с EntityGraph
        // Post postWithEntityGraph = postService.getPostByIdWithEntityGraph(postId);
        //postWithEntityGraph.getImages().forEach(image -> log.info("Image id {}, url {}", image.getId(), image.getUrl()));

        // посмотреть, что изменилось в логах запроса
    }
}
