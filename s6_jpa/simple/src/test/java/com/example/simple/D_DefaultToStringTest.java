package com.example.simple;


import com.example.simple.entity.Post;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@SpringBootTest(classes = SimpleApplication.class)
@Sql(value = "classpath:sql/init_client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class D_DefaultToStringTest {

    @Autowired
    private PostService postService;

    @Test
    void test() {
        Post postWithAllLinkedEntities = postService.getPostAndLog(1L);

        // неудобно читать, а хочется красиво логировать. Давайте добавим на Post @ToString из Lombok?
        // добавляем и запускаем заново

        // в логах опять ошибки, разбираемся почему и как сделать нормально, обсуждаем риски
    }
}
