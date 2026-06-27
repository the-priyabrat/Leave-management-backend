package com.example.trainee.bank.app.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
public @interface Valiadtedate {
	String message() default "Enter in yyyy-MM-dd format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
