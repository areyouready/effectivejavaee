package com.airhacks.doit.business.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by sebastianbasner on 15.11.15.
 */
@Documented
@Constraint(validatedBy = CrossCheckConstraintValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CrossCheck {

   String message() default "Cross check failed!";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};

}

