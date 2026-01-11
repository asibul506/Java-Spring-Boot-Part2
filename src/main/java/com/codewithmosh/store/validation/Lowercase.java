package com.codewithmosh.store.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // ensures that this annotation can only be applied to fields.
@Retention(RetentionPolicy.RUNTIME) // This is for runtime retention, meaning the annotation will be available at runtime for reflection.
@Constraint(validatedBy = LowercaseValidator.class) // Specify the validator class that will handle the validation logic for this annotation.
public @interface Lowercase {
    String message() default "must be lowercase";
    Class<?>[] groups() default {}; // Used for grouping constraints.
    Class<? extends Payload>[] payload() default {}; // Used to carry metadata information about the annotation.
}
