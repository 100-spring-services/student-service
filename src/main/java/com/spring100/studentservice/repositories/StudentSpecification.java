package com.spring100.studentservice.repositories;

import com.spring100.studentservice.models.Student;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class StudentSpecification {
    public static Specification<Student> hasDob(LocalDate dob) {
        return (root, query, criteriaBuilder) -> dob == null? null: criteriaBuilder.equal(root.get("dateOfBirth"), dob);
    }

    public static Specification<Student> dobBefore(LocalDate date) {
        return (root, query, criteriaBuilder) -> date == null? null: criteriaBuilder.lessThan(root.get("dateOfBirth"), date);
    }

    public static Specification<Student> dobAfter(LocalDate date) {
        return (root, query, criteriaBuilder) -> date == null? null: criteriaBuilder.greaterThan(root.get("dateOfBirth"), date);
    }

    public static Specification<Student> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null? null: criteriaBuilder.or(
            criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%"+name.toLowerCase()+"%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%"+name.toLowerCase()+"%")
        );
    }
}
