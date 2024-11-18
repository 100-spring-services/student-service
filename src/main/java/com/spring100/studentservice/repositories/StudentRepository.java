package com.spring100.studentservice.repositories;

import com.spring100.studentservice.models.IdentityDocumentType;
import com.spring100.studentservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {

    Student findByIdentityDocumentTypeAndIdentityDocumentId(IdentityDocumentType identityDocumentType, String identityDocumentId);
}
