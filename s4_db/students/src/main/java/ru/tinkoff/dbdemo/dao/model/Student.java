package ru.tinkoff.dbdemo.dao.model;

import lombok.*;
import org.springframework.data.annotation.*;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Student {
    @Id
    private long id;
    private final String name;
    private final String address;
    private final String department;
    private final long salary;
}
