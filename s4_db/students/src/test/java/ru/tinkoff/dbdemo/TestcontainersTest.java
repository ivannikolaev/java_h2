package ru.tinkoff.dbdemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.tinkoff.dbdemo.dao.StudentRepository;
import ru.tinkoff.dbdemo.dao.model.Student;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestcontainersTest {
    @Autowired
    StudentRepository studentRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.liquibase.changelog", () -> "liquibase/master.xml");
    }

    @BeforeAll
    static void init() {
        postgres.start();
    }

    @AfterAll
    static void destroy() {
        postgres.stop();
    }

    @Test
    public void testSaveGet() {
        Student result = studentRepository.save(new Student("A", "B", "C", 18));
        assertEquals(1L, result.getId());
        Optional<Student> saved = studentRepository.findById(1L);
        assertTrue(saved.isPresent());
        saved.ifPresent(s -> assertEquals(s, result));
    }
}
