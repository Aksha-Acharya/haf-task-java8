package com.employee.EmpData.customValidation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = AgeAboveValidator.class)
@Documented
public @interface AgeAbove {
	
	String message() default "Age must be above 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
   
}