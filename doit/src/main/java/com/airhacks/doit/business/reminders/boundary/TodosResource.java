package com.airhacks.doit.business.reminders.boundary;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 30.10.15.
 */
@Path("todos")
public class TodosResource {

   @GET
   public ToDo hello () {
      return new ToDo("implement REST endpoint", "...", 100);
   }

}
