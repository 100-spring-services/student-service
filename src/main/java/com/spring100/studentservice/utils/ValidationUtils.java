package com.spring100.studentservice.utils;

import com.spring100.studentservice.exceptions.InvalidParameterValueException;

import java.time.LocalDate;
import java.util.Random;

public class ValidationUtils {
    public static void validateDateOfBirth(LocalDate dateOfBirth, int minAge, int maxAge) {
        if(dateOfBirth == null){
            throw new InvalidParameterValueException("dateOfBirth: Date of Birth cannot be empty");
        }
        LocalDate today = LocalDate.now();

        // Ensure dateOfBirth is not in the future
        if (dateOfBirth.isAfter(today)) {
            throw new InvalidParameterValueException("dateOfBirth: Date of Birth cannot be in the future");
        }

        // Calculate age
        int age = today.getYear() - dateOfBirth.getYear();
        if (dateOfBirth.plusYears(age).isAfter(today)) {
            age--; // Adjust for birthdates later in the year
        }

        // Validate age range
        if (age < minAge) {
            throw new InvalidParameterValueException("dateOfBirth: Age must be at least " + minAge + " years");
        } else if (age > maxAge) {
            throw new InvalidParameterValueException("dateOfBirth: Age cannot exceed " + maxAge + " years");
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+?\\d{10,15}$");
    }

    public static String generateUniqueStudentCode() {
        return generateUniqueCode("STU", 8); // "STU" is the prefix for student codes
    }

    public static String generateUniqueCode(String prefix, int length) {
        // Get the current timestamp in milliseconds
        long timestamp = System.currentTimeMillis();

        // Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(1000000); // Generate a random number

        // Combine timestamp and random number to create a unique code
        String uniqueCode = String.valueOf(timestamp) + String.format("%06d", randomNumber); // Concatenate and format

        // Ensure the length does not exceed the desired length
        if (uniqueCode.length() > length) {
            uniqueCode = uniqueCode.substring(0, length); // Truncate if the length exceeds the required length
        }

        // Return the unique code with an optional prefix
        return (prefix != null ? prefix + "-" : "") + uniqueCode;
    }

}
