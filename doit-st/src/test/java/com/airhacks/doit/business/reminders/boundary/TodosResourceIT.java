package com.airhacks.doit.business.reminders.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sebastianbasner on 30.10.15.
 */
public class TodosResourceIT {

   private Client client;
   private WebTarget tut;

   @Before
   public void initClient() {
      this.client = ClientBuilder.newClient();
      this.tut = this.client.target("http://localhost:8080/doit/api/todos");
   }

   @Test
   public void fetchToDos() {
      final Response response = this.tut.request(MediaType.TEXT_PLAIN).get();
      assertThat(response.getStatus(), is(200));
      final String payload = response.readEntity(String.class);
      assertTrue(payload.startsWith("hey"));
   }
}
