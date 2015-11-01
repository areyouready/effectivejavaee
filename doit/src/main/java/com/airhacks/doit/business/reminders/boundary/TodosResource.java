package com.airhacks.doit.business.reminders.boundary;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 30.10.15.
 */
@Stateless
@Path("todos")
public class TodosResource {

   @Inject
   ToDoManager manager;

   @GET
   @Path("{id}")
   public ToDo find(@PathParam("id") long id) {
      return  this.manager.findById(id);
   }

   @DELETE
   @Path("{id}")
   public void delete(@PathParam("id") long id) {
      this.manager.delete(id);
   }

   @PUT
   @Path("{id}")
   public ToDo update(@PathParam("id") long id, ToDo todo) {
      todo.setId(id); //id from location header overrides the one from the object
      return this.manager.save(todo);
   }

   @PUT
   @Path("{id}/status") //subresource
   public ToDo statusUpdate(@PathParam("id") long id, JsonObject statusUpdate) {
      final boolean done = statusUpdate.getBoolean("done");
      return this.manager.updateStatus(id, done);
   }

   @GET
   public List<ToDo> all() {
      return this.manager.all();

   }

   @POST //POST is used for technical keys; with business keys it would be PUT
   public Response save(ToDo todo, @Context UriInfo info) {
      final ToDo saved = this.manager.save(todo);
      long id = saved.getId();
      final URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
      return Response.created(uri).build(); //according to http spec return new uri based on created ID
   }
}
