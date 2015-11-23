package com.airhacks.doit.business.reminders.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import com.airhacks.doit.business.logging.boundary.BoundaryLogger;
import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 31.10.15.
 */
@Stateless
@Interceptors(BoundaryLogger.class) //to log all the methods of the ToDoManager
public class ToDoManager {

   @PersistenceContext
   EntityManager em;

   public ToDo findById(long id) {
      return this.em.find(ToDo.class, id);
   }

   public void delete(long id) {
      try {
         final ToDo reference = this.em.getReference(ToDo.class, id); //not with find because this never gets null back (works on proxy)
         this.em.remove(reference);
      } catch (EntityNotFoundException e) {
         //we want to remove it... so we ignore this because if exception is thrown the entity did not exist anyway
      }
   }

   public List<ToDo> all() {
      return this.em.createNamedQuery(ToDo.findAll, ToDo.class).getResultList();
   }

   public ToDo save(ToDo todo) { //needs to return the ToDo Object because the generated ID is needed
      return this.em.merge(todo); //merge is used for insert and update
   }

   public ToDo updateStatus(long id, boolean done) {
      final ToDo todo = this.findById(id);
      if(todo == null) {
         return null;
      }
      todo.setDone(done);
      return todo;
   }
}
