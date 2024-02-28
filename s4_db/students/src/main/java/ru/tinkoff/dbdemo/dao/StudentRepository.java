package ru.tinkoff.dbdemo.dao;

import ru.tinkoff.dbdemo.dao.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
import ru.tinkoff.dbdemo.dao.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
