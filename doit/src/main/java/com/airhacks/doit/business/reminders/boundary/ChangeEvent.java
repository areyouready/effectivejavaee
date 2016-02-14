package com.airhacks.doit.business.reminders.boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Created by sebastianbasner on 14.02.16.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface ChangeEvent {

   Type value();

   enum Type {
      CREATION ,
      UPDATE
   }
}
