package com.example.mbticodingtask.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntervalConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntervalConstraint {

    String message() default "Invalid Interval Range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
