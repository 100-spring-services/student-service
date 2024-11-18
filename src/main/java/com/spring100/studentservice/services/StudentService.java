package com.spring100.studentservice.services;

import com.spring100.studentservice.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    Page<Student> fetchAllStudents(int page, int size, String name, LocalDate dob, LocalDate dobBefore, LocalDate dobAfter);
    Student addStudent(Student student);
    Student updateStudent(UUID id, Student student);
    void deleteStudent(UUID id);
}
