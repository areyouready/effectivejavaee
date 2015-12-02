package com.airhacks.doit.business.logging.boundary;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.airhacks.doit.business.monitoring.entity.CallEvent;

/**
 * Created by sebastianbasner on 23.11.15.
 */
public class BoundaryLogger {

   @Inject
   Event<CallEvent> monitoring;

   /**
    * Interceptor that calls the actual method and logs first.
    * @param ic context information about the intercepted invocation
    * @return call to the actualmethod
    * @throws Exception
    */
   @AroundInvoke
   public Object logCall(InvocationContext ic) throws Exception {
      long start = System.currentTimeMillis();
      try {
         return ic.proceed();
      } finally {
         long duration = System.currentTimeMillis() - start;
         monitoring.fire(new CallEvent(ic.getMethod().getName(), duration)) ;
      }
   }
}
