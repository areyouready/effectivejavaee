package com.airhacks.doit.business.monitoring.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sebastianbasner on 02.12.15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CallEvent {

   private String methodName;
   private long duration;

   public CallEvent() {
   }

   public CallEvent(String methodName, long duration) {
      this.methodName = methodName;
      this.duration = duration;
   }

   public String getMethodName() {
      return methodName;
   }

   public long getDuration() {
      return duration;
   }

   @Override
   public String toString() {
      return "CallEvent{" +
            "methodName='" + methodName + '\'' +
            ", duration=" + duration +
            '}';
   }
}
