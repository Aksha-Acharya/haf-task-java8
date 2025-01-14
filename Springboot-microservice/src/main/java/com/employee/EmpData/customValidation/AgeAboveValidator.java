package com.employee.EmpData.customValidation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeAboveValidator implements ConstraintValidator<AgeAbove, String> {

    private static final int MIN_AGE = 18;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(AgeAbove constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            return true; // Let @NotBlank or @NotNull handle empty values
        }

        LocalDate dob;
        try {
            dob = LocalDate.parse(dateOfBirth, formatter);
        } catch (Exception e) {
            return false; // If parsing fails, return false
        }

        LocalDate today = LocalDate.now();
        Period age = Period.between(dob, today);

        return age.getYears() >= MIN_AGE;
    }
}