package com.example.simple;

import com.example.simple.entity.Post;
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
public class C_MultipleBagFetchTest {

    @Autowired
    private PostService postService;

    @Test
    void test() {
        Post postWithAllLinkedEntities = postService.getPostWithAllLinkedEntities(1L);

        log.info("Retrieved post: {}", postWithAllLinkedEntities.getId());

        // в логах опять ошибки
        // https://vladmihalcea.com/hibernate-multiplebagfetchexception/ - почему решение из интернета не всегда правильное
    }
}
