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
public class ToDosResource {

   @Inject
   ToDoManager manager;

   @Path("{id}")
   public ToDoResource find(@PathParam("id") long id) {
      return new ToDoResource(id, manager);
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
