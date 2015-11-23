package com.airhacks.doit.business.logging.boundary;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by sebastianbasner on 23.11.15.
 */
public class BoundaryLogger {

   @Inject
   LogSink LOG;

   /**
    * Interceptor that calls the actual method and logs first.
    * @param ic context information about the intercepted invocation
    * @return call to the actualmethod
    * @throws Exception
    */
   @AroundInvoke
   public Object logCall(InvocationContext ic) throws Exception {
      LOG.log("--" + ic.getMethod());
      return ic.proceed();
   }
}
