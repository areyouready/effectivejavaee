package com.airhacks.doit.business.logging.boundary;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by sebastianbasner on 23.11.15.
 */
public class LogSinkProducer {

   /**
    * Implementation of the LogSink interface.
    * @param ip the injection point
    * @return the logger configured to info
    */
   @Produces
   public LogSink produce(InjectionPoint ip) {
      final Class<?> injectionTarget = ip.getMember().getDeclaringClass();
      return Logger.getLogger(injectionTarget.getName())::info; //information about wh is the calling class (BoundaryLogger i.e.)
   }
}
