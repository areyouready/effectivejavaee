package com.airhacks.doit.business.reminders.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.airhacks.doit.business.validation.CrossCheck;
import com.airhacks.doit.business.validation.ValidEntity;

/**
 * Created by sebastianbasner on 31.10.15.
 */
@Entity
@NamedQuery(name = ToDo.findAll, query = "SELECT t FROM ToDo t")
@XmlAccessorType(XmlAccessType.FIELD) // no getter/setter needed
@XmlRootElement // for serialization purposes (transfer over network)
@CrossCheck
@EntityListeners(ToDoAuditor.class)
public class ToDo implements ValidEntity {

   @Id
   @GeneratedValue
   private long id;

   static final String PREFIX = "com.airhacks.doit.business.reminders.entity.ToDo";
   public static final String findAll = PREFIX + "findAll";

   @NotNull
   @Size(min = 2, max = 256)
   private String caption;
   private String description;
   private int priority;
   private boolean done;

   @Version
   private long version;

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

   public void setCaption(String caption) {
      this.caption = caption;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setPriority(int priority) {
      this.priority = priority;
   }

   /**
    * Checks that a ToDo with priority > 10 has a description.
    * @return <code>true</code> if priority > 10 and description is present,
    *         <code>false</code> if one of the conditions is not met
    */
   @Override
   public boolean isValid() {
      if (this.priority <= 10) {
         return true;
      }
      return (this.description != null && !this.description.isEmpty());
   }

   @Override
   public String toString() {
      return "ToDo{" +
            "id=" + id +
            ", caption='" + caption + '\'' +
            ", description='" + description + '\'' +
            ", priority=" + priority +
            ", done=" + done +
            ", version=" + version +
            '}';
   }
}
