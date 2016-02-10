package com.airhacks.doit.presentation;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.airhacks.doit.business.reminders.boundary.ToDoManager;
import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 09.02.16.
 */
@Model
public class Index {
   @Inject
   ToDoManager boundary;

   ToDo todo;

   @Inject
   Validator validator;

   @PostConstruct
   public void init() {
       this.todo = new ToDo();
   }

   public ToDo getTodo() {
      return todo;
   }

   public void showValidationError(String content) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
      FacesContext.getCurrentInstance().addMessage("", message);
   }

   public Object save() {
      final Set<ConstraintViolation<ToDo>> violations = this.validator.validate(this.todo);
      for (ConstraintViolation violation : violations) {
         this.showValidationError(violation.getMessage());
      }
      if (violations.isEmpty()) {
         this.boundary.save(todo);
      }
      return null; // means to stay on the same page without further navigation
   }
}
