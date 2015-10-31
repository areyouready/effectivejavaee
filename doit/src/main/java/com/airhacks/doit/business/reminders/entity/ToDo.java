package com.airhacks.doit.business.reminders.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sebastianbasner on 31.10.15.
 */
@XmlAccessorType(XmlAccessType.FIELD) // no getter/setter needed
@XmlRootElement // for serialization purposes (transfer over network)
public class ToDo {

   private long id;
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
