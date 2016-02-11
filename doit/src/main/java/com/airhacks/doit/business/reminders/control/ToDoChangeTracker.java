package com.airhacks.doit.business.reminders.control;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 11.02.16.
 */
public class ToDoChangeTracker {

   public void onToDoChange (@Observes(during = TransactionPhase.AFTER_SUCCESS) ToDo todo) {
      System.out.println("############ todo changed and committed = [" + todo + "]");
   }
}
