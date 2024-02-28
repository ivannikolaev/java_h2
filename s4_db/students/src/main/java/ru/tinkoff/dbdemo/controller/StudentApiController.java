package ru.tinkoff.dbdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.dbdemo.dao.StudentRepository;
import ru.tinkoff.dbdemo.dao.model.Student;
import ru.tinkoff.dbdemo.model.StudentDto;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class StudentApiController {
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<StudentDto> getAll() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(s -> new StudentDto(
                        s.getName(),
                        s.getAddress(),
                        s.getDepartment(),
                        s.getSalary()
                ))
                .toList();
    }

    @PostMapping("/students/add")
    public void addStudent(@RequestBody StudentDto student) {
        studentRepository.save(new Student(
                student.name(),
                student.address(),
                student.department(),
                student.salary()
        ));
    }
}
