package com.example.trainee.bank.app.validation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Valiadtedate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String dateString = String.valueOf(value);
		String rexexp = "$[0-9]{4}-[0-9]{2}-[0-9]{2}^";
		LocalDate localDate=LocalDate.parse(value);
		LocalDate currentDate=LocalDate.now();
		System.err.println();
			return true;
	}

}
