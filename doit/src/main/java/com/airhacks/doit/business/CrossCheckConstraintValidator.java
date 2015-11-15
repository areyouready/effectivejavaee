package com.airhacks.doit.business;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by sebastianbasner on 15.11.15.
 */
public class CrossCheckConstraintValidator implements ConstraintValidator<CrossCheck, ValidEntity> {


   @Override
   public void initialize(CrossCheck constraintAnnotation) {
   }

   @Override
   public boolean isValid(ValidEntity entity, ConstraintValidatorContext context) {
      return entity.isValid();
   }
}
