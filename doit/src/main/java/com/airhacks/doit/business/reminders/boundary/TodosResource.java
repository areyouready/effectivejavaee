package com.airhacks.doit.business.reminders.boundary;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by sebastianbasner on 30.10.15.
 */
@Path("todos")
public class TodosResource {

   @GET
   public String hello () {
      return "hey " + System.currentTimeMillis();
   }

}
