package com.airhacks.doit.business.reminders.boundary;

import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Every operation that needs the ID belongs in this class.
 * Created by sebastianbasner on 01.11.15.
 */
public class ToDoResource {

   long id;
   ToDoManager manager;

   public ToDoResource(long id, ToDoManager manager) {
      this.id = id;
      this.manager = manager;
   }

   @PUT
   public ToDo save(ToDo todo) {
      todo.setId(id); //id from location header overrides the one from the object
      return this.manager.save(todo);
   }

   @GET
   public ToDo find() {
      return  this.manager.findById(id);
   }

   @DELETE
   public void delete() {
      this.manager.delete(id);
   }

   @PUT
   @Path("/status") //subresource
   public Response statusUpdate(JsonObject statusUpdate) {
      if(!statusUpdate.containsKey("done")) {
         return Response.status(Response.Status.BAD_REQUEST).
               header("reason", "JSON status should contain field done").
               build();
      }
      final boolean done = statusUpdate.getBoolean("done");
      final ToDo todo = this.manager.updateStatus(id, done);
      if(todo == null) {
         return Response.status(Response.Status.BAD_REQUEST).
               header("reason", "todo with id " + id + " does not exist").
               build();
      }else{
         return Response.ok(todo).build();
      }
   }


}
