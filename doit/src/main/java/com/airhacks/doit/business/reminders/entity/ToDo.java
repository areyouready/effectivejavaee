package com.airhacks.doit.business.reminders.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sebastianbasner on 31.10.15.
 */
@Entity
@NamedQuery(name = ToDo.findAll, query = "SELECT t FROM ToDo t")
@XmlAccessorType(XmlAccessType.FIELD) // no getter/setter needed
@XmlRootElement // for serialization purposes (transfer over network)
public class ToDo {

   @Id
   @GeneratedValue
   private long id;

   static final String PREFIX = "com.airhacks.doit.business.reminders.entity.ToDo";
   public static final String findAll = PREFIX + "findAll";

   private String caption;
   private String description;
   private int priority;

   private boolean done;

   public ToDo(String caption, String description, int priority) {
      this.caption = caption;
      this.description = description;
      this.priority = priority;
   }

   public ToDo() {
   }

   public void setId(long id) {
      this.id = id;
   }

   public long getId() {
      return id;
   }

   public String getCaption() {
      return caption;
   }

   public int getPriority() {
      return priority;
   }

   public String getDescription() {
      return description;
   }

   public boolean isDone() {
      return done;
   }

   public void setDone(boolean done) {
      this.done = done;
   }
}
