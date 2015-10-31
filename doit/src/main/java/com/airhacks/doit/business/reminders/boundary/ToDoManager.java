package com.airhacks.doit.business.reminders.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 31.10.15.
 */
@Stateless
public class ToDoManager {

   @PersistenceContext
   EntityManager em;

   public ToDo findById(long id) {
      return this.em.find(ToDo.class, id);
   }

   public void delete(long id) {
      final ToDo reference = this.em.getReference(ToDo.class, id); //not with find because this never gets null back (works on proxy)
      this.em.remove(reference);
   }

   public List<ToDo> all() {
      return this.em.createNamedQuery(ToDo.findAll, ToDo.class).getResultList();
   }

   public void save(ToDo todo) {
      this.em.merge(todo);
   }
}
