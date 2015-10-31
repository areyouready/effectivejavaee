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
      final Response postResponse = this.provider.target().request().post(Entity.json(todoToCreate));
      assertThat(postResponse.getStatus(), is(204));

      final Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
      assertThat(response.getStatus(), is(200));
      final JsonArray allTodos = response.readEntity(JsonArray.class);
      System.out.println("payload " + allTodos);
      assertFalse(allTodos.isEmpty());

      final JsonObject todo = allTodos.getJsonObject(0);
      assertTrue(todo.getString("caption").startsWith("impl"));

      //GET with id
      final JsonObject dedicatedTodo = this.provider.target().
            path("42").
            request(MediaType.APPLICATION_JSON).
            get(JsonObject.class);
      assertTrue(dedicatedTodo.getString("caption").contains("42"));

      final Response deleteResponse = this.provider.target().
            path("42").
            request(MediaType.APPLICATION_JSON).
            delete();
      assertThat(deleteResponse.getStatus(), is(204));

   }
}
