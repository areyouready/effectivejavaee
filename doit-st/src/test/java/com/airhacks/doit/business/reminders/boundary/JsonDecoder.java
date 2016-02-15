package com.airhacks.doit.business.reminders.boundary;

import java.io.IOException;
import java.io.Reader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * A Decoder for JSon Messages.
 * The same decoder would be usable in the server as the encoder would be usable on the client.
 * Created by sebastianbasner on 15.02.16.
 */
public class JsonDecoder implements Decoder.TextStream<JsonObject> {

   @Override
   public void init(EndpointConfig endpointConfig) {
      //not needed here
   }

   @Override
   public JsonObject decode(Reader reader) throws DecodeException, IOException {
      //The try-with-resources statement ensures that each resource is closed at the end of the statement.
      try(JsonReader jsonReader =  Json.createReader(reader)) {
         return jsonReader.readObject();
      }
   }

   @Override
   public void destroy() {
      //not needed here
   }
}
