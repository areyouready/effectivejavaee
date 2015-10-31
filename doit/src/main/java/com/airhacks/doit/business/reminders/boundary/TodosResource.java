package com.airhacks.doit.business.reminders.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 30.10.15.
 */
@Path("todos")
public class TodosResource {

   @GET
   @Path("{id}")
   public ToDo find(@PathParam("id") long id) {
      return new ToDo("implement REST endpoint " + id, "...", 100);
   }

   @DELETE
   @Path("{id}")
   public void delete(@PathParam("id") long id) {
      System.out.println("deleted= " + id);
   }

   @GET
   public List<ToDo> all() {
      List<ToDo> all = new ArrayList<>();
      all.add(find(42));
      return all;
   }

   @POST //POST is used for technical keys; with business keys it would be PUT
   public void save(ToDo todo) {
      System.out.println("todo = [" + todo + "]");

   }
}
