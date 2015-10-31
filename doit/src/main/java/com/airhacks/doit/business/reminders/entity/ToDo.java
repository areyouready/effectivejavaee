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

   public ToDo(String caption, String description, int priority) {
      this.caption = caption;
      this.description = description;
      this.priority = priority;
   }

   public ToDo() {
   }
}
