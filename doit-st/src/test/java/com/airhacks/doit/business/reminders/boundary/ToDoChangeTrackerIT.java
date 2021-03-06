package com.airhacks.doit.business.reminders.boundary;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.json.JsonObject;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class that listens to websockets.
 * Created by sebastianbasner on 13.02.16.
 */
public class ToDoChangeTrackerIT {

   private WebSocketContainer container;
   private ChangesListener listener;

   @Before
   public void initContainer() throws IOException, DeploymentException, URISyntaxException {
      this.container = ContainerProvider.getWebSocketContainer();
      URI uri = new URI("ws://localhost:8080/doit/changes");
      this.listener = new ChangesListener();
      ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().
            decoders(Arrays.asList(JsonDecoder.class)).
            build();
      this.container.connectToServer(this.listener, cec, uri);
   }

   @Test
   public void receiveNotifications () throws InterruptedException {
      final JsonObject message = this.listener.getMessage();
      assertNotNull(message);
      System.out.println("message from test = " + message);
   }

}