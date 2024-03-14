package com.example.simple;

import com.example.simple.entity.Post;
import com.example.simple.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@SpringBootTest(classes = SimpleApplication.class)
@Sql(value = "classpath:sql/init_client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/cleanDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class F_BidirectionalTest {

    @Autowired
    private PostService service;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {
        // Запускаем и смотрим логи
        Post post1 = service.saveWithRelation(1L);

        // разбираемся почему так

        // смотрим на связь "с другой стороны"
        //Post post2 = service.saveWithRelationOtherSide(1L);
        // вроде работает, но какая проблема? (связь есть при сохранении, но в данной сессии на "старом" Post ее нет

        // смотрим, что будет, если переприсваивать коллекцию
        //Post post3 = service.saveWithRelationIncorrectCollectionAllocation(1L);
        //Post post3FromDb =  service.getPostByIdWithEntityGraphComments(1L);

        // работать не будет из-за orphan removal

        // и посмотрим корректное
        //Post post4 = service.saveWithRelationCorrectCollectionAllocation(2L);
        // Post post4FromDb =  service.getPostByIdWithEntityGraphComments(2L);

        // реализовать вместе корректные сеттеры для коллекций и связей
    }


}
