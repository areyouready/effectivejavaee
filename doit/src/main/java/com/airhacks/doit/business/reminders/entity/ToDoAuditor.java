package com.airhacks.doit.business.reminders.entity;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import com.airhacks.doit.business.reminders.boundary.ChangeEvent;

/**
 * Intercepts events and transforms them to CDI events.
 * Created by sebastianbasner on 11.02.16.
 */
public class ToDoAuditor {

   @Inject @ChangeEvent(ChangeEvent.Type.CREATION)
   Event<ToDo> create;

   @Inject @ChangeEvent(ChangeEvent.Type.UPDATE)
   Event<ToDo> update;

   @PostPersist
   public void onPersist(ToDo todo) {
      this.create.fire(todo);
   }

   @PostUpdate
   public void onUpdate(ToDo todo) {
      this.update.fire(todo);
   }
}
