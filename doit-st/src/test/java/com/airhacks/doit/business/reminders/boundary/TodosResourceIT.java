package com.airhacks.doit.business.reminders.boundary;

import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;

/**
 * Created by sebastianbasner on 30.10.15.
 */
public class TodosResourceIT {

   @Rule
   public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/doit/api/todos");

   @Test
   public void crud() {
      //create
      JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
      final JsonObject todoToCreate = todoBuilder.
            add("caption", "implement").
            add("priority", 42).
            build();

      //create
      final Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
      assertThat(postResponse.getStatus(), is(201));
      final String location = postResponse.getHeaderString("Location"); //the new uri
      System.out.println("location = " + location);

      //find
      //GET with id
      final JsonObject dedicatedTodo = this.provider.client().
            target(location).
            request(MediaType.APPLICATION_JSON).
            get(JsonObject.class);
      assertTrue(dedicatedTodo.getString("caption").contains("implement")); //should match the created above

      //update
      JsonObjectBuilder updateBuilder = Json.createObjectBuilder();
      JsonObject updated = updateBuilder.
            add("caption", "implemented").
            build();

      Response updatedResponse = this.provider.client().
            target(location).
            request(MediaType.APPLICATION_JSON).
            put(Entity.json(updated));
      assertThat(updatedResponse.getStatus(), is(200));

      //update again (Version has changed)
      updateBuilder = Json.createObjectBuilder();
      updated = updateBuilder.
            add("caption", "implemented").
            add("priority", 42).
            build();

      updatedResponse = this.provider.client().
            target(location).
            request(MediaType.APPLICATION_JSON).
            put(Entity.json(updated));
      assertThat(updatedResponse.getStatus(), is(409)); //409 is set in EJBExceptionMapper
      final String conflictInformation = updatedResponse.getHeaderString("cause");
      assertNotNull(conflictInformation);
      System.out.println("conflictInformation " + conflictInformation);

      //find it again
      //find
      JsonObject updatedTodo = this.provider.client().
            target(location).
            request(MediaType.APPLICATION_JSON).
            get(JsonObject.class);
      assertTrue(updatedTodo.getString("caption").contains("implemented"));

      //update status
      //update
      JsonObjectBuilder statusBuilder = Json.createObjectBuilder();
      JsonObject status = statusBuilder.
            add("done", true).
            build();

      this.provider.client().
            target(location).
            path("status"). //use the subresource
            request(MediaType.APPLICATION_JSON).
            put(Entity.json(status));

      //verify status
      updatedTodo = this.provider.client().
            target(location).
            request(MediaType.APPLICATION_JSON).
            get(JsonObject.class);
      assertThat(updatedTodo.getBoolean("done"), is(true));


      //update not existing status
      JsonObjectBuilder notExistingBuilder = Json.createObjectBuilder();
      status = notExistingBuilder.
            add("done", true).
            build();

      Response response = this.provider.target().
            path("-42"). //does not exist
            path("status"). //use the subresource
            request(MediaType.APPLICATION_JSON).
            put(Entity.json(status));
      assertThat(response.getStatus(), is(400)); //Bad Request
      assertFalse(response.getHeaderString("reason").isEmpty());

      //update malformed status
      notExistingBuilder = Json.createObjectBuilder();
      status = notExistingBuilder.
            add("something wrong", true).
            build();

      response = this.provider.
            client().
            target(location).
            path("status"). //use the subresource
            request(MediaType.APPLICATION_JSON).
            put(Entity.json(status));
      assertThat(response.getStatus(), is(400)); //Bad Request
      assertFalse(response.getHeaderString("reason").isEmpty());


      //findAll
      response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
      assertThat(response.getStatus(), is(200));
      final JsonArray allTodos = response.readEntity(JsonArray.class);
      System.out.println("payload " + allTodos);
      assertFalse(allTodos.isEmpty());

      final JsonObject todo = allTodos.getJsonObject(0);
      assertTrue(todo.getString("caption").startsWith("impl"));

      //deleting not existing
      final Response deleteResponse = this.provider.target().
            path("42").
            request(MediaType.APPLICATION_JSON).
            delete();
      assertThat(deleteResponse.getStatus(), is(204));
   }

   @Test
   public void createToDoWithoutCaption() {
      //create
      JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
      final JsonObject todoToCreate = todoBuilder.
            add("priority", 42).
            build();

      final Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
      assertThat(postResponse.getStatus(), is(400));
      postResponse.getHeaders().entrySet().forEach(System.out::println); //Validation exception in header
   }

   @Test
   public void createValidToDo() {
      //create
      JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
      final JsonObject todoToCreate = todoBuilder.
            add("caption", "12").
            add("priority", 42).
            build();

      final Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
      assertThat(postResponse.getStatus(), is(201));
      postResponse.getHeaders().entrySet().forEach(System.out::println);
   }
}
