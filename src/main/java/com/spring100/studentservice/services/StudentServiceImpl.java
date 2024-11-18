package com.spring100.studentservice.services;

import com.spring100.studentservice.exceptions.InvalidParameterValueException;
import com.spring100.studentservice.models.IdentityDocumentType;
import com.spring100.studentservice.models.Student;
import com.spring100.studentservice.repositories.StudentRepository;
import com.spring100.studentservice.repositories.StudentSpecification;
import com.spring100.studentservice.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> fetchAllStudents(int page, int size, String name, LocalDate dob, LocalDate dobBefore, LocalDate dobAfter) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Student> spec = Specification.where(StudentSpecification.hasName(name))
                .and(StudentSpecification.hasDob(dob))
                .and(StudentSpecification.dobBefore(dobBefore))
                .and(StudentSpecification.dobAfter(dobAfter));
        return studentRepository.findAll(spec, pageable);
    }

    @Override
    public Student addStudent(Student student) {
        validateStudent(student);

        if(student.getPermanentAddress() == null || student.getPermanentAddress().isEmpty()) {
            student.setPermanentAddress(student.getPresentAddress());
        }
        // Generate unique student code
        student.setStudentCode(ValidationUtils.generateUniqueStudentCode());

        // Save student
        return studentRepository.save(student);
    }

    // Update an existing student
    @Override
    public Student updateStudent(UUID id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterValueException("Student not found"));
        student.setId(id);
        validateStudent(student);

        // Update fields
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        existingStudent.setPresentAddress(student.getPresentAddress());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setIdentityDocumentType(student.getIdentityDocumentType());
        existingStudent.setIdentityDocumentId(student.getIdentityDocumentId());

        // Save updated student
        return studentRepository.save(existingStudent);
    }

    // Delete a student
    @Override
    public void deleteStudent(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterValueException("Student not found"));

        studentRepository.delete(student);
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new InvalidParameterValueException("body: Student object cannot be null");
        }

        ValidationUtils.validateDateOfBirth(student.getDateOfBirth(), 4, 120);

        // Validation logic moved to utility method
        validateMandatoryFields(student);
        validateContactInfo(student);
        validateDocumentInfo(student);

    }

    private void validateMandatoryFields(Student student) {
        if (isNullOrBlank(student.getFirstName())) {
            throw new InvalidParameterValueException("firstName: First name cannot be null or blank");
        }
        if (isNullOrBlank(student.getLastName())) {
            throw new InvalidParameterValueException("lastName: Last name cannot be null or blank");
        }
        if (isNullOrBlank(student.getEmail())) {
            throw new InvalidParameterValueException("email: Email cannot be null or blank");
        }
        if (isNullOrBlank(student.getPhoneNumber())) {
            throw new InvalidParameterValueException("phoneNumber: Phone number cannot be null or blank");
        }
        if (isNullOrBlank(student.getPresentAddress())) {
            throw new InvalidParameterValueException("presentAddress: Present address cannot be null or blank");
        }
        if (isNullOrBlank(student.getIdentityDocumentId())) {
            throw new InvalidParameterValueException("identityDocumentId: Identity document id cannot be null or blank");
        }
    }

    private void validateContactInfo(Student student) {
        if (!ValidationUtils.isValidEmail(student.getEmail())) {
            throw new InvalidParameterValueException("email: Invalid email format");
        }
        if (!ValidationUtils.isValidPhoneNumber(student.getPhoneNumber())) {
            throw new InvalidParameterValueException("phoneNumber: Invalid phone number format");
        }
    }

    private void validateDocumentInfo(Student student) {
        if (student.getIdentityDocumentType() == null) {
            throw new InvalidParameterValueException("identityDocumentType: Identity document type cannot be null");
        }

        try {
            IdentityDocumentType.valueOf(student.getIdentityDocumentType().toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterValueException("identityDocumentType: Invalid identity document type");
        }
        if(student.getIdentityDocumentId() != null && student.getIdentityDocumentType() != null) {
            Student existingStudent = studentRepository.findByIdentityDocumentTypeAndIdentityDocumentId(student.getIdentityDocumentType(), student.getIdentityDocumentId());
            if (existingStudent != null && !existingStudent.equals(student)) {
                throw new InvalidParameterValueException("identityDocumentId: Identity document ID is already in use");
            }
        }
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public Student fetchStudentById(UUID id) {
        if(id == null) {
            throw new InvalidParameterValueException("id cannot be null");
        }
        return studentRepository.findById(id).orElseThrow(() -> new InvalidParameterValueException("Student not found"));
    }
}
