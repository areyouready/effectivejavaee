package com.airhacks.doit.business.reminders.boundary;

import java.io.IOException;
import java.io.Writer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * A encoder for JSon Messages.
 * Created by sebastianbasner on 14.02.16.
 */
public class JsonEncoder implements Encoder.TextStream<JsonObject> {

   @Override
   public void init(EndpointConfig endpointConfig) {
      //not needed here
   }

   @Override
   public void encode(JsonObject payload, Writer writer) throws EncodeException, IOException {
      try (JsonWriter jsonWriter = Json.createWriter(writer)) {
         jsonWriter.writeObject(payload);
      }
   }

   @Override
   public void destroy() {
      //not needed here
   }
}
