package com.airhacks.doit.business;

import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Catches all EJB Exceptions.
 * Created by sebastianbasner on 01.11.15.
 */
@Provider //needed for JAX-RS to use this
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

   @Override
   public Response toResponse(EJBException exception) {
      final Throwable cause = exception.getCause();
      Response unknownError = Response.serverError().
            header("cause", exception.toString()).build();
      if(cause == null){ //possible edge case of undefined EJBException...
         return unknownError;
      }

      if(cause instanceof OptimisticLockException) {
         return Response.status(Response.Status.CONFLICT).
               header("cause", "conflict occured " + cause).
               build();
      }
      return unknownError;
   }
}
