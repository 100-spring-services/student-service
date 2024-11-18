package com.spring100.studentservice.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String studentCode;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityDocumentId;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String fatherName;
    private String motherName;
    private String guardianName;
    private String presentAddress;
    private String permanentAddress;

    // Default Constructor
    public Student() {}

    // Parameterized Constructor
    public Student(
            UUID id,
            String studentCode,
            String firstName,
            String lastName,
            IdentityDocumentType identityDocumentType,
            String identityDocumentId,
            LocalDate dateOfBirth,
            String email,
            String phoneNumber,
            String fatherName,
            String motherName,
            String guardianName,
            String presentAddress,
            String permanentAddress
    ) {
        this.id = id;
        this.studentCode = studentCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityDocumentType = identityDocumentType;
        this.identityDocumentId = identityDocumentId;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.guardianName = guardianName;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public IdentityDocumentType getIdentityDocumentType() {
        return identityDocumentType;
    }

    public void setIdentityDocumentType(IdentityDocumentType identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }

    public String getIdentityDocumentId() {
        return identityDocumentId;
    }

    public void setIdentityDocumentId(String identityDocumentId) {
        this.identityDocumentId = identityDocumentId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName.toLowerCase();
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName.toLowerCase();
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName.toLowerCase();
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress.toLowerCase();
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress.toLowerCase();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + studentCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identityDocumentType='" + identityDocumentType + '\'' +
                ", identityDocumentId='" + identityDocumentId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same instance
        if (o == null || getClass() != o.getClass()) return false; // Null or different class
        Student student = (Student) o;
        return id != null && id.equals(student.id); // Compare IDs
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0; // Hash based on ID
    }

}
