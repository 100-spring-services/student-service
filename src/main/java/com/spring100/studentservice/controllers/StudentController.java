package com.spring100.studentservice.controllers;

import com.spring100.studentservice.models.Student;
import com.spring100.studentservice.services.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(path = "/students")
@Validated
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    // Fetch all students with pagination
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dob,
            @RequestParam(value = "dobBefore", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dobBefore,
            @RequestParam(value = "dobAfter", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dobAfter
    ) {
        Page<Student> students = studentService.fetchAllStudents(page, size, name, dob, dobBefore, dobAfter);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        Student student = studentService.fetchStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Create a new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Validated Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.status(201).body(createdStudent);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable UUID id, @RequestBody @Validated Student student
    ) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}

