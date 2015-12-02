package com.airhacks.doit.business.monitoring.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.airhacks.doit.business.monitoring.entity.CallEvent;

/**
 * Created by sebastianbasner on 02.12.15.
 */
@Stateless
@Path("boundary-invocations")
public class BoundaryInvocationsResource {

   @Inject
   MonitorSink ms;

   @GET
   public List<CallEvent> expose() {
      return this.ms.getRecentEvents();
   }
}
