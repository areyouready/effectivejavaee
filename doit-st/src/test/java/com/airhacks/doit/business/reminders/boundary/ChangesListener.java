package com.airhacks.doit.business.reminders.boundary;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.json.JsonObject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 * Created by sebastianbasner on 13.02.16.
 */
public class ChangesListener extends Endpoint {

   JsonObject message;

   //block and wait for 1 message
   CountDownLatch latch = new CountDownLatch(1);

   @Override
   public void onOpen(Session session, EndpointConfig endpointConfig) {
      session.addMessageHandler(JsonObject.class, msg -> {
         message = msg;
         latch.countDown();
         System.out.println("msg = " + msg);
      });
   }

   public JsonObject getMessage() throws InterruptedException {
      latch.await(1, TimeUnit.MINUTES); //wait for 1 minute and throw exception after that
      return message;
   }
}
